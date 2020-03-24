<template>
  <div>
    <Card>
      <Breadcrumb>
        <BreadcrumbItem to="/">Index</BreadcrumbItem>
        <BreadcrumbItem>pageDetail</BreadcrumbItem>
      </Breadcrumb>
    </Card>
    <Card style="margin: 10px;padding: 10px;">
      <Row>
        <Col span="24" align="center" style="margin-bottom: 10px;">
            <p style="font-size: 32px;">{{title}}</p>
        </Col>
      </Row>
      <Row>
        <Col style="margin-left: 45px;margin-right: 45px;">
          <div v-html="content" />
        </Col>
      </Row>
    </Card>
  </div>
</template>

<script>

  export default {
    name: "pageDetail",
    data() {
      return {
        articleId: '',
        title: '',
        content: '',
      }
    },
    created() {
      this.getDetail();
    },
    mounted() {

    },
    methods: {
      getDetail: function () {
        this.articleId = localStorage.getItem("articleId");
        this.axios.get("/articleController/getDetail", {
          params: {
            articleId: this.articleId
          }
        }).then(res => {
          if (this.exception(res)) {
            this.title = res.data.data.title;
            this.content = this.md2html(res.data.data.content);
          }
        });
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
  }
</script>

<style scoped>

</style>
