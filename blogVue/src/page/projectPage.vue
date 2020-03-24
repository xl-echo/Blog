<template>
  <div class="spikeActivity">
    <Row>
      <Col span="24">
        <Card>
          <Breadcrumb>
            <BreadcrumbItem to="/">Index</BreadcrumbItem>
            <BreadcrumbItem>spikeActivity</BreadcrumbItem>
          </Breadcrumb>
        </Card>
      </Col>
    </Row>
    <Row style="margin-top: 10px">
      <Col span="2">&nbsp;</Col>
      <Col span="20">
        <Row>
          <Col span="24">
            <Card>
              项目名称：{{projectInfo.projectName}}
            </Card>
          </Col>
        </Row>
        <Row>
          <Col span="24">
            <Card>
              商品名称：{{productInfo.productName}}
            </Card>
          </Col>
        </Row>
        <Row>
          <Col span="24">
            <Card>
              <Row>
                <Col span="6">
                  <img style="width: 60%;height: 60%" :src="productInfo.productImg"/>
                </Col>
                <Col span="6">
                  商品数量：<Input style="width: 30%" type="text" v-model="productNumber"/><br/><br/>
                  商品价格：<Input style="width: 30%" type="text" :value="productInfo.productPrice"/>
                </Col>
                <Col span="12">
                  商品介绍：<br/>
                  <Input type="text" :value="productInfo.productDesc"/>
                </Col>
              </Row>
              <br/>
              <Row>
                <Col span="24">
                  当前抢购并发量：<Input style="width: 10%;" type="text" v-model="threadNumber"/>
                  <Button type="primary" @click="runProject">发起并发演示</Button>
                </Col>
              </Row>
              <br/>
              <Row>
                <Col span="24">
                  <p>当前商品订单：</p>
                  <Table :data="tableData" :columns="tableColumns"/><br/>
                  <Page align="right" :total="total" :current="currentPage" @on-change="changePage" show-total></Page>
                </Col>
              </Row>
            </Card>
          </Col>
        </Row>
      </Col>
      <Col span="2">&nbsp;</Col>
    </Row>
  </div>
</template>

<script>
  import {formatDate} from "../utils/formatDateUtils";

  export default {
    name: "spikeActivity",
    data() {
      return {
        total: 0,
        currentPage: 1,
        page: 1,
        size: 10,
        productNumber: 0,
        threadNumber: 1000,
        projectInfo: '',
        productInfo: '',
        tableData: [],
        tableColumns: [
          {
            title: "订单编号",
            align: "center",
            key: "id"
          },
          {
            title: "用户名称",
            align: "center",
            key: "username",
            render: (h, params) => {
              let username = params.row.username;
              if (username == '' || username == null) {
                username = "echo";
              }
              return h("span", username);
            }
          },
          {
            title: "收货地址",
            align: "center",
            key: "address"
          },
          {
            title: "是否付款",
            align: "center",
            key: "ifPay",
            render: (h, params) => {
              let ifPay = params.row.ifPay;
              if (ifPay == 0) {
                ifPay = "否";
              } else {
                ifPay = "是";
              }
              return h("span", ifPay);
            }
          },
          {
            title: "下单时间",
            align: "center",
            key: "createTime",
            render: (h, params) => {
              return h(
                "span",
                formatDate(
                  new Date(params.row.createTime),
                  "yyyy-MM-dd hh:mm:ss"
                )
              );
            }
          },
        ],
      }
    },
    created() {
      let id = localStorage.getItem("projectDetailId");
      this.project(id);
      // 如果id是1证明点击的是秒杀项目
      if (id == 1) {
        this.getProduct();
        this.orderGetList();
      }
    },
    methods: {
      changePage: function (row) {
        this.page = row;
        this.orderGetList();
      },
      runProject: function () {
        let authorId = localStorage.getItem("authorId");
        this.axios({
          method: "post",
          url: "/orderController/add",
          headers: {"Content-type": "application/json"},
          data: {
            userId: authorId,
            productId: 1,
            address: '湖南省岳阳市',
          },
        }).then(res => {
          if (this.exception(res)) {
            this.getProductNumber();
            this.orderGetList();
          }
        });
      },
      getProductNumber: function() {
        this.axios.get("/productController/getProductNumber", {}).then(res => {
          if (this.exception(res)) {
            this.productNumber = res.data.data;
          }
        });
      },
      orderGetList: function () {
        this.axios.get("/orderController/getList", {
          params: {
            page: this.page,
            size: 10
          }
        }).then(res => {
          if (this.exception(res)) {
            this.tableData = res.data.data.list;
            this.total = res.data.data.total;
          }
        });
      },
      getProduct: function () {
        this.axios.get("/productController/getProduct", {}).then(res => {
          if (this.exception(res)) {
            this.productInfo = res.data.data;
          }
        });
        this.axios.get("/productController/getProductNumber", {}).then(res => {
          if (this.exception(res)) {
            this.productNumber = res.data.data;
          }
        });
      },
      project: function (id) {
        this.axios.get("/projectCollectionController/getByIdProject", {
          params: {
            id: id
          }
        }).then(res => {
          if (this.exception(res)) {
            this.projectInfo = res.data.data;
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
