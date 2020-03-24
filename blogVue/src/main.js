// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css';
import ViewUI from 'view-design';
import './my-theme/index.less';

Vue.use(ViewUI)
Vue.use(iView)
Vue.config.productionTip = false

import axios from 'axios';
Vue.prototype.axios = axios;
axios.defaults.baseURL = 'http://localhost:10080';

// markdown
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
Vue.use(mavonEditor);

// markdown显示
import showdown from 'showdown'
import hljs from 'highlight.js/lib/highlight';
import 'highlight.js/styles/monokai-sublime.css';
var showdownHighlight = require("showdown-highlight")
Vue.prototype.md2html = (md)=> {
  let converter = new showdown.Converter({
    extensions:[showdownHighlight]
  })
  let text = md.toString()
  let html = converter.makeHtml(text)
  return html
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
