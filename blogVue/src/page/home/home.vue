<template>
  <Row>
    <Col span="16">
      <Card style="margin: 10px;">
        <Row>
          <Col span="24" v-for="(item, index) in contentList" :key="index">
            <Card style="margin: 10px;">
              <Button type="primary" size="small">{{item.ifOriginal | formatType}}</Button>
              <B style="margin-left: 6px;font-size: 16px" @click="detail(item.id)">{{item.title}}</B>
              <p style="margin-top: 5px">{{item.content}}</p>
              <p style="margin-top: 5px">{{item.createTime | formatDate}}&nbsp;&nbsp;阅读数: {{item.clicks}}</p>
            </Card>
          </Col>
        </Row>
      </Card>
    </Col>
    <Col span="8">
      <Row>
        <Col span="24">
          <Card style="margin: 10px;">
            <Carousel autoplay v-model="carouselValue" loop autoplay-speed="5000" @on-click="carouselMethod">
              <CarouselItem>
                <img style="width: 100%;height: 80%" src="https://i.loli.net/2020/02/21/oiSVk92ItUYF4PK.png"
                     data-holder-rendered="true">
              </CarouselItem>
              <CarouselItem>
                <img style="width: 100%;height: 80%" src="https://i.loli.net/2020/02/21/Y2Sm613DRIkzw9g.png"
                     data-holder-rendered="true">
              </CarouselItem>
            </Carousel>
          </Card>
        </Col>
      </Row>
      <Row>
        <Col span="24">
          <Card style="margin: 10px;">
            <p style="margin-bottom: 10px;margin-left: 10px">
              <Icon type="md-flame" size="24"/>&nbsp;最热文章
            </p>
            <Row>
              <Col span="24" v-for="(item, index) in articleList" :key="index">
                <li style="margin-top: 5px;margin-left: 15px"><a @click="detail(item.id)">{{item.title}}</a></li>
              </Col>
            </Row>
          </Card>
        </Col>
      </Row>
      <Row>
        <Col span="24">
          <Card style="margin: 10px;">
            <p style="font-size: 18px;margin-left: 15px">快速认识博主</p>
            <Row>
              <Col span="6"><img style="height: 80%;width: 80%;margin-top: 10px;margin-left: 15px"
                                 src="https://i.loli.net/2020/02/13/cWtrX9Ddnwbp7Nv.png"/></Col>
              <Col span="18">
                <Row>
                  <Col span="24">&nbsp;</Col>
                  <Col span="24">
                    <p>一个热爱网络技术的靓仔，是echo的忠实粉丝。</p>
                    <p>邮箱地址：xl_echo@163.com</p>
                    <p>微信号码：t2421499075</p>
                    <p>有更多问题可以加我反馈</p>
                  </Col>
                </Row>
              </Col>
            </Row>
            <Divider/>
            <Icon type="logo-github" size="30" style="margin-left: 15px"
                  @click="clickMethod('https://github.com/xl-echo')"/>
            <Icon type="ios-at" size="30" style="margin-left: 15px"
                  @click="clickMethod('https://xlsorry.coding.net/user/projects')"/>
            <Icon type="logo-octocat" size="30" style="margin-left: 15px"
                  @click="clickMethod('https://xlsorry.coding.net/user/projects')"/>
            <Icon type="logo-twitter" size="30" style="margin-left: 15px"/>
          </Card>
        </Col>
      </Row>
    </Col>
  </Row>
</template>

<script>
  export default {
    name: "home",
    data() {
      return {
        page: 0,
        size: 10,
        contentList: [],
        articleList: [],
        dateValue: new Date(),
        carouselValue: 0,
      }
    },
    created() {
      this.pageList();
    },
    methods: {
      carouselMethod: function () {
        if (this.carouselValue == 0) {
          window.open("https://time.geekbang.org/course/intro/100042601?code=LQ%2FHzhYAu7TzyBjoAuPP84-zm7L-OW16zFzqfELna-w%3D", '_target')
        } else {
          window.open("https://time.geekbang.org/course/intro/100023501?code=d1se%2F7ugeBEyuU%2FIYp1ynfSZa6ulbGhhDK%2Fkpn3-lFc%3D", '_target')
        }
      },
      pageList: function () {
        this.axios.get("/articleController/getList", {
          params: {
            page: this.page,
            size: 10
          }
        }).then(res => {
          if (this.exception(res)) {
            this.contentList = res.data.data
          }
        });
        this.axios.get("/articleController/getHotList", {}).then(res => {
          if (this.exception(res)) {
            this.articleList = res.data.data
          }
        })
      },
      detail: function (id) {
        localStorage.setItem("articleId", id);
        this.$router.push('/pageDetail');
      },
      clickMethod: function (url) {
        window.open(url, '_target');
      },
      exception: function (res) {
        if (res.data.status === 999) {
          this.$Modal.info({
            title: "请求失败",
            content: res.data.message
          });
          return false;
        } else if (res.data.status === 1000) {
          this.$Modal.info({
            title: "温馨提示",
            content: res.data.message
          });
          return false;
        } else if (res.data.status === 200) {
          return true;
        }
      }
    },
    filters: {
      formatDate: function (value) {
        let date = new Date(value);
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? "0" + MM : MM;
        let d = date.getDate();
        d = d < 10 ? "0" + d : d;
        let h = date.getHours();
        h = h < 10 ? "0" + h : h;
        let m = date.getMinutes();
        m = m < 10 ? "0" + m : m;
        let s = date.getSeconds();
        s = s < 10 ? "0" + s : s;
        return y + "-" + MM + "-" + d + " " + h + ":" + m + ":" + s;
      },
      formatType: function (value) {
        let type = value == 0 ? "原创" : value == 1 ? "转载" : "翻译";
        return type;
      }
    }
  }
</script>

<style scoped>

</style>
