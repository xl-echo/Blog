<template>
  <div class="container">
    <div>
      <Card>
        <Breadcrumb>
          <BreadcrumbItem to="/">Index</BreadcrumbItem>
          <BreadcrumbItem>pageDetail</BreadcrumbItem>
        </Breadcrumb>
      </Card>
      <Card style="margin-top: 20px">
        <Row>
          <Col span="8">
            <Menu :theme="themeUser" :active-name="activeName" @on-select="select">
              <Submenu name="1">
                <template slot="title">
                  <Icon type="ios-paper"/>
                  个人资料管理
                </template>
                <MenuItem name="userDetail">个人资料</MenuItem>
              </Submenu>
              <Submenu name="2">
                <template slot="title">
                  <Icon type="ios-people"/>
                  博客管理
                </template>
                <MenuItem name="releaseBlog">已发布博客</MenuItem>
                <MenuItem name="unReleaseBlog">未发布博客</MenuItem>
              </Submenu>
              <Submenu name="3">
                <template slot="title">
                  <Icon type="ios-stats"/>
                  统计分析
                </template>
                <MenuItem name="clickNumber">点击量</MenuItem>
              </Submenu>
            </Menu>
          </Col>
          <Col span="16">
            <userDetail v-if="userDetailBoolean"/>
            <releaseBlog v-if="releaseBoolean"/>
            <unReleaseBlog v-if="unReleaseBlogBoolean"/>
            <clickNumber v-if="clickNumberBoolean"/>
          </Col>
        </Row>
      </Card>
    </div>
  </div>
</template>

<script>
import userDetail from '@/page/user/userDetail'
import releaseBlog from '@/page/user/releaseBlog'
import unReleaseBlog from '@/page/user/unReleaseBlog'
import clickNumber from '@/page/user/clickNumber'

export default {
  name: 'userInfo',
  components: {
    'userDetail': userDetail,
    'releaseBlog': releaseBlog,
    'unReleaseBlog': unReleaseBlog,
    'clickNumber': clickNumber
  },
  data() {
    return {
      themeUser: 'light',
      activeName: '',
      userDetailBoolean: false,
      releaseBoolean: false,
      unReleaseBlogBoolean: false,
      clickNumberBoolean: false
    }
  },
  methods: {
    select(name) {
      this.activeName = name
      this.userDetailBoolean = name === 'userDetail'
      this.releaseBoolean = name === 'releaseBlog'
      this.unReleaseBlogBoolean = name === 'unReleaseBlog'
      this.clickNumberBoolean = name === 'clickNumber'
    }
  }
}
</script>

<style scoped>
</style>
