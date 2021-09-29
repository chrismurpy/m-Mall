# m-Mall：一款B2C模式的电商平台

### 项目介绍
- m-Mall是一个电商平台实战项目，包括前台商城页面及后台管理系统，基于`SpringBoot+SpringCloud+MyBatis-Plus`实现，后台`MySQL、ElasticSearch、Redis`等组件采用Docker容器化部署。前台商城页面包含首页门户、广告投放、商品搜索、商品展示、购物车、订单流程、会员中心等模块。后台管理系统包含商品管理、订单管理、组织结构管理、规格参数管理、权限管理等模块。
---
### 项目运行
- 前端项目：进入项目所在文件夹，删除`node_modules`目录。在终端执行`cnpm install`进行重新安装依赖，随后执行`npm run dev`运行前端项目

- 后台项目：通过Docker部署相关环境
  
  - `mysql:5.7.13` - 3306
  - `redis:5.0` - 6379
  - `elasticsearch:6.6.2` - 9200 / 9300
  - `kibana:6.6.2` - 5601
  - `canal/canal-server` - 11111
  - `morunchang/fastdfs`
  - `morunchang/fastdfs`
  - `rabbitmq:management` - 15672
---
### 运行效果
##### 1. 前台商城门户

![效果1](https://github.com/chrismurpy/m-Mall/blob/master/Pic/4%E9%A1%B9%E7%9B%AE%E5%89%8D%E5%8F%B0.png)

##### 2. 后台管理系统

![效果2](https://github.com/chrismurpy/m-Mall/blob/master/Pic/5%E9%A1%B9%E7%9B%AE%E5%90%8E%E5%8F%B0.png)

---
