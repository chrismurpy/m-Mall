<template>
  <div>
    <div class="search-nav">
      <div class="search-nav-container">
        <ul>
          <li>全部分类</li>
          <li><router-link to="/">首页</router-link></li>
          <li><router-link to="/">中西药品</router-link></li>
          <li><router-link to="/">家庭常备药</router-link></li>
          <li><router-link to="/">医疗器械</router-link></li>
          <li><router-link to="/">美妆个护</router-link></li>
          <li><router-link to="/">维生素钙</router-link></li>
          <li><router-link to="/">健康电器</router-link></li>
          <li><router-link to="/">健康理疗</router-link></li>
          <li><router-link to="/">体检门诊</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GoodsListNav'
};
</script>

<style scoped>
.search-nav{
  width: 100%;
  height: 64px;
  border-bottom: 2px solid #ff9d9e;
}
.search-nav-container{
  width: 80%;
  min-width: 1000px;
  height: 64px;
  margin: 0px auto;
}
.search-nav-container-90{
  width: 90%;
}
.search-nav-container ul{
  margin: 0px;
  padding-left: 0px;
  list-style: none;
}
.search-nav-container li{
  cursor: pointer;
  margin-left: 30px;
  line-height: 64px;
  color: #C81623;
  font-size: 18px;
  /*font-weight: bold;*/
  float: left;
}
.search-nav-container a{
  color: #ff6b75;
  text-decoration: none;
}
.search-nav-container li:first-child{
  padding: 0px 38px;
  background: #ea6c6c;
  margin: 0px;
  color: #fff;
}
</style>
