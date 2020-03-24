启动一个可持久化数据数据库
```liunx
docker run -d --name mysql001 -p 127.0.0.1:3306:3306 -v /use/local/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --privileged mysql:5.7

# 如果已經存在了該鏡像，直接使用下面的命令
docker restart mysql001
```

### 启动mysql持久化进程之后，不到5秒进程自动消失了。解决办法
首先我们将tutum-docker-mysql跑起来.

```docker run -d -p 3306:3306 --name mysql tutum/mysql```
如果你本地没有tutum/mysql的image,docker会先下载它的image,这一步可能会用些时间.待执行完毕我们检查一下应该会有如下样子
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200130144938214.png)

tutum-docker-mysql会自动帮我们建一个随机的密码供我们访问,通过日志可以查看到.
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200130145048182.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hsZWNobw==,size_16,color_FFFFFF,t_70)

我们通过日志上的密码登陆mysql
```
docker exec -it 容器id /bin/bash
mysql -uadmin -ppigGBWIe9HKd
```
登陆成功后证明我们前面的docker镜像启动的步骤都没有问题，这个时候我们需要停掉docker镜像设定指定的映射持久化文件

- 首先我们停掉之前的Container
```
docker stop mysql
```

我们指定一个本地可以挂载的路径,重新启动tutum-docker-mysql.我们指定/use/local/mysql 挂到Container里的/var/lib/mysql目录(-v Bind mount a volume). 这样子我们就可以将数据持久化在主机(Host)的目录中了.
```
sudo docker run -d -p 3306:3306 -v /use/local/mysql:/var/lib/mysql -e MYSQL_PASS="123456" tutum/mysql
```

我们在上面启动的时候顺便指定了创建密码为123456,我们现在登陆一下mysql创建一些数据,看看会不会被保存下来

- 启动成功之后我们就可以直接连接了，创建一个数据库试试，然后停止container，并使用如下命令再次启动。
```
sudo docker run -d -p 3306:3306 --restart always -v /use/local/mysql:/var/lib/mysql -e MYSQL_PASS="123456" tutum/mysql
```
我们会发现我们原来创建的数据库数据都在这里。

### 启动一个持久化数据的redis
```
 docker run -d --privileged=true -p 6379:6379 --restart always -v /usr/local/redis/redis.conf:/etc/redis/redis.conf -v /usr/local/redis/data:/data --name myredis redis redis-server /etc/redis/redis.conf --appendonly yes --requirepass "123456"
```

### 涉及到的命令行参数
```js
-d                                                  -> 以守护进程的方式启动容器
-p 6379:6379                                        -> 绑定宿主机端口
--name myredis                                      -> 指定容器名称
--restart always                                    -> 开机启动
--privileged=true                                   -> 提升容器内权限
-v /root/docker/redis/conf:/etc/redis/redis.conf    -> 映射配置文件
-v /root/docker/redis/data:/data                    -> 映射数据目录
--appendonly yes                                    -> 开启数据持久化
---requirepass "123456"                             -> 链接密码
```

