<template>
  <div>
    <Row>
      <Col span="24" style="margin: 10px">
        <B style="color: #464c5b;font-size: 16px">文章标题:</B>
        <Input v-model="articleParam.title" placeholder="请输入文章标题..." style="width: 75%;margin-left: 6px" size="large"/>
        <Button style="margin-left: 10px" size="large" @click="saveContent">保存草稿</Button>
        <Button type="primary" size="large" @click="releaseContent">发布文章</Button>
        <Button type="primary" size="large">
          {{articleParam.author}}
          <Icon type="logo-octocat" size="24"/>
        </Button>
      </Col>
    </Row>
    <Row>
      <Col span="24">
        <mavon-editor ref="editor" v-model="articleParam.content" style="min-height: 800px;min-width: 800px"/>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "addBlog",
    data() {
      return {
        articleParam: {
          content: '',
          title: '',
          token: '',
          authorId: '',
          author: '',
          ifOriginal: 0,
        },
      }
    },
    created() {
      this.articleParam.author = localStorage.getItem("username");
    },
    methods: {
      saveContent: function () {

      },
      releaseContent: function () {
        console.log(this.articleParam.title)
        if (this.articleParam.title == null || this.articleParam.title == '') {
          this.$Modal.info({
            title: "温馨提示",
            content: "标题不能为空"
          });
          return;
        }
        if (this.articleParam.content == null || this.articleParam.content == '') {
          this.$Modal.info({
            title: "温馨提示",
            content: "内容不能为空"
          });
          return;
        }
        this.articleParam.authorId = localStorage.getItem("authorId");
        this.articleParam.token = localStorage.getItem("token");

        this.axios({
          method: "post",
          url: "/articleController",
          headers: {"Content-type": "application/json"},
          data: this.articleParam
        }).then(res => {
          if (this.exception(res)) {
            this.$router.push('/')
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
    }
  }
</script>

<style scoped>

</style>
