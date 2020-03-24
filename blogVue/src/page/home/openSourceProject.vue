<template>
  <div class="openSourceProject">
    <Row>
      <Col style="margin-top: 10px;margin-bottom: 10px" align="center" span="4" v-for="i in item" :key="i.id">
        <Card align="center" style="width: 85%;height: 85%;">
          <Row><Col><p style="font-size: 20px">{{i.projectName}}</p></Col></Row>
          <Row>
            <img style="width: 96%" src="https://i.loli.net/2020/02/13/JvrRCP5ayfkT69O.png"/>
          </Row>
          <Row><Col style="margin-top: 10px">HTTPS:&nbsp;<Input v-model="i.https" style="width: 70%" /></Col></Row>
          <Row><Col style="margin-top: 10px">SSH:&nbsp;<Input v-model="i.ssh" style="width: 80%" /></Col></Row>
          <Row><Col style="margin-top: 10px"><Button type="primary" @click="projectDetail(i)" long>进入项目</Button></Col></Row>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "openSourceProject",
    data() {
      return {
        https: 'https://e.coding.net/xlsorry/mybatis.git',
        ssh: 'git@e.coding.net:xlsorry/mybatis.git',
        item: null,
      }
    },
    created() {
      this.pageList();
    },
    methods: {
      projectDetail: function(project) {
        localStorage.setItem("projectDetailId", project.id);
        this.$router.push('/projectPage');
      },
      pageList: function () {
        this.axios.get("/projectCollectionController/getList", {}).then(res => {
          if (this.exception(res)) {
            this.item = res.data.data;
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
