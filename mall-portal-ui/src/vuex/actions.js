// 获取轮播(营销)图片
export const loadCarouselItems = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = {
      carouselItems: [
        '/img/nav/1.jpg',
        '/img/nav/3.jpg',
      ],
      activity: [
        '/img/nav/nav_showimg1.jpg',
      ]
    };
    commit('SET_CAROUSELITEMS_INFO', data);
  });
};

// 加载电脑专栏数据
// export const loadComputer = ({ commit }) => {
//   return new Promise((resolve, reject) => {
//     const computer = {
//       title: '电脑数码',
//       link: [ '电脑馆', '游戏极品', '装机大师', '职场焕新', '女神频道', '虚拟现实', '二合一平板', '电子教育', '万物周刊' ],
//       detail: [
//         {
//           bigImg: '/img/index/computer/item-computer-1.jpg',
//           itemFour: [
//             {
//               title: '电脑馆',
//               intro: '笔记本999元限量秒！',
//               img: '/img/index/computer/item-computer-2.jpg'
//             },
//             {
//               title: '外设装备',
//               intro: '1000减618',
//               img: '/img/index/computer/item-computer-1-3.jpg'
//             },
//             {
//               title: '电脑配件',
//               intro: '联合满减最高省618',
//               img: '/img/index/computer/item-computer-1-4.jpg'
//             },
//             {
//               title: '办公生活',
//               intro: '5折神券 精品文具',
//               img: '/img/index/computer/item-computer-1-5.jpg'
//             }
//           ],
//           itemContent: [
//             '/img/index/computer/item-computer-1-6.jpg',
//             '/img/index/computer/item-computer-1-7.jpg',
//             '/img/index/computer/item-computer-1-8.jpg'
//           ]
//         },
//         {
//           bigImg: '/img/index/computer/item-computer-2-1.jpg',
//           itemFour: [
//             {
//               title: '平板电脑',
//               intro: '爆款平板12期免息',
//               img: '/img/index/computer/item-computer-2-2.jpg'
//             },
//             {
//               title: '智能酷玩',
//               intro: '抢999减666神券',
//               img: '/img/index/computer/item-computer-2-3.jpg'
//             },
//             {
//               title: '娱乐影音',
//               intro: '大牌耳机低至5折',
//               img: '/img/index/computer/item-computer-2-4.jpg'
//             },
//             {
//               title: '摄影摄像',
//               intro: '大牌相机5折抢',
//               img: '/img/index/computer/item-computer-2-5.jpg'
//             }
//           ],
//           itemContent: [
//             '/img/index/computer/item-computer-2-6.jpg',
//             '/img/index/computer/item-computer-2-7.jpg',
//             '/img/index/computer/item-computer-2-8.jpg'
//           ]
//         }
//       ]
//     };
//     commit('SET_COMPUTER_INFO', computer);
//   });
// };
//
// // 加载爱吃专栏数据
// export const loadEat = ({ commit }) => {
//   return new Promise((resolve, reject) => {
//     const eat = {
//       title: '爱吃',
//       link: [ '休闲零食', '坚果', '牛奶', '饮料冲调', '食用油', '大米', '白酒', '红酒', '烧烤食材', '牛排', '樱桃' ],
//       detail: [
//         {
//           bigImg: '/img/index/eat/item-eat-1-1.jpg',
//           itemFour: [
//             {
//               title: '粮油调味',
//               intro: '买2免1',
//               img: '/img/index/eat/item-eat-1-2.jpg'
//             },
//             {
//               title: '饮料冲调',
//               intro: '第二件半价',
//               img: '/img/index/eat/item-eat-1-3.jpg'
//             },
//             {
//               title: '休闲零食',
//               intro: '满99减40',
//               img: '/img/index/eat/item-eat-1-4.jpg'
//             },
//             {
//               title: '中外名酒',
//               intro: '满199减100',
//               img: '/img/index/eat/item-eat-1-5.jpg'
//             }
//           ],
//           itemContent: [
//             '/img/index/eat/item-eat-1-6.jpg',
//             '/img/index/eat/item-eat-1-7.jpg',
//             '/img/index/eat/item-eat-1-8.jpg'
//           ]
//         },
//         {
//           bigImg: '/img/index/eat/item-eat-2-1.jpg',
//           itemFour: [
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: '/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: '/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: '/img/index/eat/item-eat-2-2.jpg'
//             },
//             {
//               title: '东家菜',
//               intro: '丰富好味',
//               img: '/img/index/eat/item-eat-2-2.jpg'
//             }
//           ],
//           itemContent: [
//             '/img/index/eat/item-eat-2-6.jpg',
//             '/img/index/eat/item-eat-2-7.jpg',
//             '/img/index/eat/item-eat-2-8.jpg'
//           ]
//         }
//       ]
//     };
//     commit('SET_EAT_INFO', eat);
//   });
// };

// 请求获得商品详细信息
export const loadGoodsInfo = ({ commit }) => {
  commit('SET_LOAD_STATUS', true);
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = {
        goodsImg: [
          '/img/goodsDetail/item-detail-1.jpg',
          '/img/goodsDetail/item-detail-2.jpg',
          '/img/goodsDetail/item-detail-3.jpg',
          '/img/goodsDetail/item-detail-4.jpg'
        ],
        title: '苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳',
        tags: ['满69-20元', '关注产品★送钢化膜', 'BIT配次日达'],
        discount: ['满148减10', '满218减20', '满288减30'],
        promotion: ['跨店满减', '多买优惠'],
        remarksNum: 6000,
        setMeal: [
          [
            {
              img: '/img/goodsDetail/pack/1.jpg',
              intro: '4.7英寸-深邃蓝',
              price: 28.0
            },
            {
              img: '/img/goodsDetail/pack/2.jpg',
              intro: '4.7英寸-星空黑',
              price: 29.0
            },
            {
              img: '/img/goodsDetail/pack/3.jpg',
              intro: '5.5英寸-香槟金',
              price: 28.5
            }
          ],
          [
            {
              img: '/img/goodsDetail/pack/4.jpg',
              intro: '5.5英寸-玫瑰金',
              price: 32.0
            },
            {
              img: '/img/goodsDetail/pack/5.jpg',
              intro: '5.5英寸-深邃蓝',
              price: 32.0
            },
            {
              img: '/img/goodsDetail/pack/6.jpg',
              intro: '5.5英寸-星空黑',
              price: 35.0
            }
          ],
          [
            {
              img: '/img/goodsDetail/pack/7.jpg',
              intro: '4.7英寸-香槟金',
              price: 26.0
            },
            {
              img: '/img/goodsDetail/pack/8.jpg',
              intro: '4.7英寸-玫瑰金',
              price: 25.0
            },
            {
              img: '/img/goodsDetail/pack/9.jpg',
              intro: '4.7英寸-中国红',
              price: 28.0
            }
          ]
        ],
        hot: [
          {
            img: '/img/goodsDetail/hot/1.jpg',
            price: 28.0,
            sale: 165076
          },
          {
            img: '/img/goodsDetail/hot/2.jpg',
            price: 36.0,
            sale: 135078
          },
          {
            img: '/img/goodsDetail/hot/3.jpg',
            price: 38.0,
            sale: 105073
          },
          {
            img: '/img/goodsDetail/hot/4.jpg',
            price: 39.0,
            sale: 95079
          },
          {
            img: '/img/goodsDetail/hot/5.jpg',
            price: 25.0,
            sale: 5077
          },
          {
            img: '/img/goodsDetail/hot/6.jpg',
            price: 20.0,
            sale: 3077
          }
        ],
        goodsDetail: [
          '/img/goodsDetail/intro/1.jpg',
          '/img/goodsDetail/intro/2.jpg',
          '/img/goodsDetail/intro/3.jpg',
          '/img/goodsDetail/intro/4.jpg'
        ],
        param: [
          {
            title: '商品名称',
            content: 'iPhone 7手机壳'
          },
          {
            title: '商品编号',
            content: '10435663237'
          },
          {
            title: '店铺',
            content: 'Gavin Shop'
          },
          {
            title: '商品毛重',
            content: '100.00g'
          },
          {
            title: '商品产地',
            content: '中国大陆'
          },
          {
            title: '机型',
            content: 'iPhone 7'
          },
          {
            title: '材质',
            content: 'PC/塑料'
          },
          {
            title: '款式',
            content: '软壳'
          },
          {
            title: '适用人群',
            content: '通用'
          }
        ],
        remarks: {
          goodAnalyse: 90,
          remarksTags: [ '颜色可人', '实惠优选', '严丝合缝', '极致轻薄', '质量没话说', '比定做还合适', '完美品质', '正品行货', '包装有档次', '不容易发热', '已经买第二个', '是全覆盖' ],
          remarksNumDetail: [ 2000, 3000, 900, 1 ],
          detail: [
            {
              username: 'p****1',
              values: 3,
              content: '颜色很好看，质量也不错！，还送了个指环，想不到哦！',
              goods: '4.7英寸-深邃蓝',
              create_at: '2018-05-15 09:20'
            },
            {
              username: '13****1',
              values: 5,
              content: '手感没的说，是硬壳，后背带有磨砂手感。很不错，很喜欢，还加送了钢化膜，支架环，物超所值，准备再买一个。很满意。物流很快。很愉快的一次购物！',
              goods: '5.5英寸-玫瑰金',
              create_at: '2018-05-13 15:23'
            },
            {
              username: '3****z',
              values: 4.5,
              content: '相当轻薄，店家还送了一大堆配件，*元非常值得！',
              goods: '4.7英寸-玫瑰金',
              create_at: '2018-05-05 12:25'
            },
            {
              username: 'gd****c',
              values: 3.5,
              content: '就是我想要的手机壳，壳子很薄，手感不错，就像没装手机壳一样，想要裸机手感的赶快下手了。',
              goods: '4.7英寸-中国红',
              create_at: '2018-04-06 16:23'
            },
            {
              username: 'r****b',
              values: 4.5,
              content: '壳子还不错，送的膜也可以，不过还是感觉膜小了那么一点，屏幕没法完全覆盖。对了，壳子稍微有点硬，可能会伤漆，所以不要频繁取壳就好。',
              goods: '4.7英寸-中国红',
              create_at: '2018-03-15 19:24'
            },
            {
              username: 'd****e',
              values: 5,
              content: '磨砂的，相当漂亮，尺寸非常合适！精工细作！',
              goods: '5.5英寸-星空黑',
              create_at: '2018-03-10 10:13'
            }
          ]
        }
      };
      commit('SET_GOODS_INFO', data);
      commit('SET_LOAD_STATUS', false);
    }, 300);
  });
};

// 获取商品列表
export const loadGoodsList = ({ commit }) => {
  commit('SET_LOAD_STATUS', true);
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = {
        asItems: [
          {
            intro: '【京东超市】清风（APP）抽纸 原木纯品金装系列 2层',
            img: '/img/index/seckill/seckill-item4.jpg',
            price: 19.9,
            num: 499,
            sale: 199
          },
          {
            // img: '/img/goodsList/阿胶.png',
            price: 108,
            intro: '东阿阿胶 阿胶补血颗粒 4g*30袋',
            num: 599,
            sale: 299
          },
          {
            intro: '【京东超市】清风（APP）抽纸 原木纯品金装系列 3层',
            img: '/img/index/seckill/seckill-item4.jpg',
            price: 39.9,
            num: 599,
            sale: 199
          }
        ],
        goodsList: [
          {
            img: '/img/goodsList/21金维他.png',
            price: 58.50,
            intro: '21金维他 多维元素片(21) 100片/瓶',
            remarks: 58,
            shopName: '金维他专营店',
            sale: 107
          },
          {
            img: '/img/goodsList/B族维生素片',
            price: 159.0,
            intro: '汤臣倍健 B族维生素片 500mg*100片*2瓶',
            remarks: 3000,
            shopName: '汤臣倍健旗舰店',
            sale: 281
          }
        ]
      };
      commit('SET_GOODS_LIST', data);
      commit('SET_LOAD_STATUS', false);
    });
  });
};

// 添加购物车
export const addShoppingCart = ({ commit }, data) => {
  return new Promise((resolve, reject) => {
    commit('ADD_SHOPPING_CART', data);
  });
};

// 获取用户推荐
export const loadRecommend = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = [
      [
        {
          img: '/img/goodsDetail/pack/999.png',
          intro: '三九999感冒灵颗粒 * 1份装',
          price: 9.9
        },
      ],
      [
        {
          img: '/img/goodsDetail/pack/999.png',
          intro: '三九999感冒灵颗粒 * 1份装',
          price: 9.9
        }
      ]
    ];
    commit('SET_RECOMMEND_INFO', data);
  });
};

export const loadAddress = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const address = [
      {
        addressId: '324000',
        name: 'Murphy',
        province: '浙江省',
        city: '杭州市',
        area: '余杭区',
        address: '仓前街道良睦路633号',
        phone: '158****0609',
        postalcode: '510000'
      },
      {
        addressId: '123458',
        name: 'Tina',
        province: '江苏省',
        city: '无锡市',
        area: '锡山区',
        address: '安镇街道锡山大道333号',
        phone: '158****0888',
        postalcode: '200120'
      }
    ];
    commit('SET_USER_ADDRESS', address);
  });
};

export const loadShoppingCart = ({ commit }) => {
  return new Promise((resolve, reject) => {
    const data = [{
      goods_id: 1529931938150,
      count: 1,
      img: '/img/goodsDetail/pack/999.png',
      package: '10g*9袋',
      price: 9.9,
      title: '三九999感冒灵颗粒 * 1份装'
    }];
    commit('SET_SHOPPING_CART', data);
  });
};

// 添加注册用户
export const addSignUpUser = ({ commit }, data) => {
  return new Promise((resolve, reject) => {
    const userArr = localStorage.getItem('users');
    let users = [];
    if (userArr) {
      users = JSON.parse(userArr);
    }
    users.push(data);
    localStorage.setItem('users', JSON.stringify(users));
  });
};

// 用户登录
export const login = ({ commit }, data) => {
  return new Promise((resolve, reject) => {
    if (data.username === 'murphy' && data.password === '123456') {
      localStorage.setItem('loginInfo', JSON.stringify(data));
      commit('SET_USER_LOGIN_INFO', data);
      resolve(true);
      return true;
    }
    const userArr = localStorage.getItem('users');
    console.log(userArr);
    if (userArr) {
      const users = JSON.parse(userArr);
      for (const item of users) {
        if (item.username === data.username) {
          localStorage.setItem('loginInfo', JSON.stringify(item));
          commit('SET_USER_LOGIN_INFO', item);
          resolve(true);
          break;
        }
      }
    } else {
      resolve(false);
    }
  });
};

// 退出登陆
export const signOut = ({ commit }) => {
  localStorage.removeItem('loginInfo');
  commit('SET_USER_LOGIN_INFO', {});
};

// 判断是否登陆
export const isLogin = ({ commit }) => {
  const user = localStorage.getItem('loginInfo');
  if (user) {
    commit('SET_USER_LOGIN_INFO', JSON.parse(user));
  }
};
