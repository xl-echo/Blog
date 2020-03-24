<template>
  <div class="openSourceBooks">
    <Row>
      <Col span="4" align="center" style="margin-top: 20px;margin-bottom: 20px" v-for="item in bookList" :key="item.id">
        <Card align="center" style="height: 300px;width: 80%;">
          <Row>
            <Col span="24">
              {{item.bookName}}
            </Col>
          </Row>
          <Row>
            <Col span="24">
              <img :src="item.bookImgLink" style="margin-top: 10px;width: 130px;height: 160px;"/>
            </Col>
          </Row>
          <Row>
            <Col span="24">
              <Button style="margin-top: 10px" @click="downLoad(item.bookName)">点击即可下载</Button>
            </Col>
          </Row>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "openSourceBooks",
    data() {
      return {
        bookList: [],
      }
    },
    created() {
      this.pageList();
    },
    methods: {
      pageList: function () {
        this.axios.get("/booksController/getList", {}).then(res => {
          if (this.exception(res)) {
            this.bookList = res.data.data
          }
        });
      },
      downLoad: function (name) {
        window.open(" http://localhost:10080/downloadController/downPdf?fileName=" + name,'_target');
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
    }
  }
</script>

<style scoped>

</style>
