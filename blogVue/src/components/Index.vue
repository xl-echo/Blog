<template>
  <div class="Index">
    <Row>
      <Menu mode="horizontal" :theme="theme1" :active-name="activeName" @on-select="select">
        <Col span="22">
          <MenuItem name="0"></MenuItem>
          <MenuItem name="home">首页</MenuItem>
          <MenuItem name="openSourceBooks">开源书籍</MenuItem>
          <MenuItem name="openSourceProject">开源项目</MenuItem>
          <MenuItem name="links">友链</MenuItem>
          <MenuItem name="about">关于</MenuItem>
        </Col>
        <Col span="2" style="float: right">
          <Button v-if="loginBefore" @click="gotoLogin">请登录</Button>
          <Dropdown v-if="loginAfter" @on-click="dropdownMethod">
            <Button type="primary">
              {{username}}
              <Icon type="logo-octocat" size="24"/>
            </Button>
            <DropdownMenu slot="list" align="center">
              <DropdownItem name="userInfo">个人主页</DropdownItem>
              <DropdownItem name="addBlog">写博客</DropdownItem>
              <DropdownItem name="blogManagement">博客管理</DropdownItem>
              <DropdownItem name="loginOut">退出</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        </Col>
      </Menu>
    </Row>
    <index-home v-if="homeBoolean"/>
    <index-openSourceBooks v-if="openSourceBooksBoolean"/>
    <index-openSourceProject v-if="openSourceProjectBoolean" />
    <index-links v-if="linksBoolean" />
    <index-about v-if="aboutBoolean" />
    <index-footer/>
  </div>
</template>

<script>
  import home from '../page/home/home.vue'
  import iFooter from '../page/home/iFooter.vue'
  import openSourceBooks from '../page/home/openSourceBooks.vue'
  import openSourceProject from '../page/home/openSourceProject.vue'
  import links from '../page/home/links.vue'
  import about from '../page/home/about.vue'

  export default {
    name: 'Index',
    components: {
      'index-home': home,
      'index-footer': iFooter,
      'index-openSourceBooks': openSourceBooks,
      'index-openSourceProject': openSourceProject,
      'index-links': links,
      'index-about': about,
    },
    data() {
      return {
        homeBoolean: true,
        openSourceBooksBoolean: false,
        openSourceProjectBoolean: false,
        linksBoolean: false,
        aboutBoolean: false,

        loginBefore: true,
        loginAfter: false,
        theme1: 'light',
        activeName: 'home',
        username: '',
      }
    },
    created() {
      this.ifLogin();
    },
    methods: {
      select: function (name) {
        this.activeName = name;

        if (name === 'home') {
          this.homeBoolean = true;
          this.openSourceBooksBoolean = false;
          this.openSourceProjectBoolean = false;
          this.linksBoolean = false;
          this.aboutBoolean = false;
        } else if (name === 'openSourceBooks') {
          this.homeBoolean = false;
          this.openSourceBooksBoolean = true;
          this.openSourceProjectBoolean = false;
          this.linksBoolean = false;
          this.aboutBoolean = false;
        } else if (name === 'openSourceProject') {
          this.homeBoolean = false;
          this.openSourceBooksBoolean = false;
          this.openSourceProjectBoolean = true;
          this.linksBoolean = false;
          this.aboutBoolean = false;
        } else if (name === 'links') {
          this.homeBoolean = false;
          this.openSourceBooksBoolean = false;
          this.openSourceProjectBoolean = false;
          this.linksBoolean = true;
          this.aboutBoolean = false;
        } else if (name === 'about') {
          this.homeBoolean = false;
          this.openSourceBooksBoolean = false;
          this.openSourceProjectBoolean = false;
          this.linksBoolean = false;
          this.aboutBoolean = true;
        }
      },
      ifLogin: function () {
        let token = localStorage.getItem("token");
        if (token == '' || token == null) {
          this.loginBefore = true;
          this.loginAfter = false;
        } else {
          this.loginBefore = false;
          this.loginAfter = true;
          this.username = localStorage.getItem("username");
        }
      },
      gotoLogin: function () {
        this.$router.push('/login')
      },
      dropdownMethod: function (name) {
        if (name == 'userInfo') {
          this.$router.push('/userInfo')
        } else if (name == 'addBlog') {
          this.$router.push('/addBlog')
        } else if (name == 'blogManagement') {
          alert('blogManagement')
        } else if (name == 'loginOut') {
          this.loginOut();
        }
      },
      loginOut: function () {
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        location.reload();
        this.$router.go(0);
      },
    },
    filters: {}
  }
</script>

<style scoped>

</style>
