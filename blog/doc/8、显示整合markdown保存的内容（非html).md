echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> 由于我们保存的时候没有对markdown内容进行转码，所以我们显示的时候需要转一次码，具体步骤如下

- 安装依赖库
```js
cnpm i markdown-loader html-loader --save
cnpm i showdown --save 
cnpm install showdown-highlight --save
cnpm install highlight.js --save
```

- main.js添加函数
```js
// markdown显示
import showdown from 'showdown'
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
```

- 对我们读取到的内容进行转码
```js
this.content = this.md2html(res.data.data.content);
```

- 在模板中显示转码的内容
```html
<div v-html="content" />
```