echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> 在我们常见的博客中，我们可以看到很多的技术博客都是支持markdown语法的，所以这里我们也整合markdown。整合markdown相对较为简单，但是有一些需要注意的地方。具体的操作步骤如下。

- vue安装markdown依赖
```
npm install mavon-editor --save
```

- 在vue中的main.js使用markdown
```vue
// markdown
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
Vue.use(mavonEditor);
```

- 编写一个添加博客页面，使用markdown的组件
```vue
<Row>
  <Col span="24">
    <mavon-editor ref="editor" v-model="articleParam.content" style="min-height: 800px;min-width: 800px"/>
  </Col>
</Row>

// 在页面中引用
import {mavonEditor} from "mavon-editor";
import "mavon-editor/dist/css/index.css";
```

- 通过请求发送到后台，注意这里发送使用postjson，如果使用postform或者get会出现如下错误：
```java
java.lang.IllegalArgumentException: Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
	at org.apache.coyote.http11.Http11InputBuffer.parseRequestLine(Http11InputBuffer.java:468) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:292) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:861) [tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1579) [tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_221]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_221]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_221]
```

> 出现这个错误的原因很简单，是用于我们的postfrom或者get都会将我们的参数拼接到我们的地址栏，但是我们的地址栏能输入的内容有限，而且不能含有部分特殊字符。markdown本来就是支持特殊语法的，所以会造成参数报错。这里我们使用postjson传参。

具体前端发送添加文章请求如下
```js
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
```

- 后端接收添加文章请求
```java
@RequestMapping(value = "", method = RequestMethod.POST)
public Result<String> save(@RequestBody ArticleParam articleParam) {
    Result<String> result = new Result<>();
    String response = articleService.save(articleParam);
    Result.packageResultMethod(result, response);
    return result;
}
```

