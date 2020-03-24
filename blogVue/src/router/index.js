import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import login from '@/components/login'
import addBlog from '@/page/addBlog'
import projectPage from '@/page/projectPage'
import pageDetail from '@/page/pageDetail'
import home from '@/page/home/home'
import userInfo from '@/page/user/userInfo'
import userDetail from '@/page/user/userDetail'
import releaseBlog from '@/page/user/releaseBlog'
import unReleaseBlog from '@/page/user/unReleaseBlog'
import clickNumber from '@/page/user/clickNumber'
import iFooter from '@/page/home/iFooter'
import openSourceBooks from '@/page/home/openSourceBooks'
import openSourceProject from '@/page/home/openSourceProject'
import register from '@/components/register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '/userInfo',
      name: 'userInfo',
      component: userInfo
    },
    {
      path: '/userDetail',
      name: 'userDetail',
      component: userDetail
    },
    {
      path: '/clickNumber',
      name: 'clickNumber',
      component: clickNumber
    },
    {
      path: '/releaseBlog',
      name: 'releaseBlog',
      component: releaseBlog
    },
    {
      path: '/unReleaseBlog',
      name: 'unReleaseBlog',
      component: unReleaseBlog
    },
    {
      path: '/openSourceBooks',
      name: 'openSourceBooks',
      component: openSourceBooks
    },
    {
      path: '/openSourceProject',
      name: 'openSourceProject',
      component: openSourceProject
    },
    {
      path: '/projectPage',
      name: 'projectPage',
      component: projectPage
    },
    {
      path: '/iFooter',
      name: 'iFooter',
      component: iFooter
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/addBlog',
      name: 'addBlog',
      component: addBlog
    },
    {
      path: '/pageDetail',
      name: 'pageDetail',
      component: pageDetail
    },
    {
      path: '/register',
      name: 'register',
      component: register
    }
  ]
})
