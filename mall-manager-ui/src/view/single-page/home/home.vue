<template>
  <div>
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="8" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-pie style="height: 300px;" :value="pieData" text="用户访问来源"></chart-pie>
        </Card>
      </i-col>
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-bar style="height: 300px;" :value="barData" text="周用户活跃量"/>
        </Card>
      </i-col>
    </Row>
    <Row>
      <Card shadow>
        <example style="height: 310px;"/>
      </Card>
    </Row>
  </div>
</template>

<script>
import InforCard from '_c/info-card'
import CountTo from '_c/count-to'
import { ChartPie, ChartBar } from '_c/charts'
import Example from './example.vue'
export default {
  name: 'home',
  components: {
    InforCard,
    CountTo,
    ChartPie,
    ChartBar,
    Example
  },
  data () {
    return {
      inforCardData: [
        { title: '新增用户', icon: 'md-person-add', count: 100, color: '#2d8cf0' },
        { title: '累计点击', icon: 'md-locate', count: 200, color: '#19be6b' },
        { title: '新增问答', icon: 'md-help-circle', count: 300, color: '#ff9900' },
        { title: '分享统计', icon: 'md-share', count: 400, color: '#ed3f14' },
        { title: '新增互动', icon: 'md-chatbubbles', count: 20, color: '#E46CBB' },
        { title: '新增页面', icon: 'md-map', count: 10, color: '#9A66E4' }
      ],
      pieData: [
        { value: 300, name: '直接访问' },
        { value: 200, name: '广告营销' },
        { value: 100, name: '视频广告' },
        { value: 1500, name: '搜索引擎' },
        { value: 300, name: '医药门店'},
        { value: 500, name: '线下诊所'}
      ],
      barData: {
        Mon: 10000,
        Tue: 35000,
        Wed: 25000,
        Thu: 12000,
        Fri: 24000,
        Sat: 1800,
        Sun: 3000
      }
    }
  },
  mounted () {
    //
  }
}
</script>

<style lang="less">
.count-style{
  font-size: 50px;
}
</style>
