// 字段排序函数
const compare = property => {
  return function (a, b) {
    var value1 = a[property];
    var value2 = b[property];
    return value1 - value2;
  };
};

// 获取排序后的列表
export const orderGoodsList = state => {
  return state.goodsList.sort(compare(state.orderBy));
};
