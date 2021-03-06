layui.use(['layer', 'laydate', 'form', 'table', 'excel'], function () {
    var $ = layui.$,
        layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        excel = layui.excel;

    //日期时间选择范围
    laydate.render({
        elem: '#rangeTime1', //指定元素
        trigger: 'click',
        range: true //开启日期范围，默认使用“_”分割
    });

    // 验证金额
    form.verify({
        money: function (value) {
            if (value.length > 0) {
                var reg = /(^[0-9]([0-9]+)?(\.[0-9]+)$)|(^(0){1}$)|(^([0-9]+)?$)/;
                if (!reg.test(value)) {
                    return '输入格式有误';
                }
            }
            if (value.length > 50) {
                return '长度大于50！请重新输入';
            }
        }

    });

    var cols = [[
        {
            field: 'orderId',
            title: '订单号',
            align: 'center',
            sort: true,
            totalRowText: '合计：'
        }, {
            field: 'userName',
            title: '用户名',
            align: 'center',
            sort: true
        }, {
            field: 'mobile',
            title: '手机号',
            align: 'center',
            sort: true
        }, {
            field: 'transId',
            title: '支付流水',
            align: 'center',
            sort: true
        }, {
            field: 'productCode',
            title: '基金代码',
            align: 'center',
            sort: true
        }, {
            field: 'trsAmount',
            title: '交易金额(元)',
            align: 'center',
            sort: true,
            totalRow: true
        }, {
            field: 'rtxnTypeCode',
            title: '交易类型',
            align: 'center',
            sort: true,
            templet: function (d) {
                /*initiative:投资者主动解冻
                productFail:未满标解冻
                productTenderFinish：产品完成解冻*/
                if (d.rtxnTypeCode != undefined) {
                    if (d.rtxnTypeCode === 'CPDJ') {
                        return "产品购买-冻结";
                    } else if (d.rtxnTypeCode === 'CPJD') {
                        return "产品流标-解冻";
                    } else if (d.rtxnTypeCode === 'CPKK') {
                        return "产品满标-扣款";
                    } else if (d.rtxnTypeCode === 'CPHK') {
                        return "产品回款";
                    }

                } else {
                    return "未知";
                }
            }
        }, {
            field: 'tradeStatus',
            title: '交易状态',
            align: 'center',
            fixed: "right",
            sort: true,
            templet: function (d) {
                /*init 初始状态
                fail投标失败
                tender投标中
                cancel投标解冻
                repaying回款中
                repayed已结清
                prepayed 提前还款结清*/
                if (d.tradeStatus != undefined) {
                    if (d.tradeStatus === '0') {
                        return "交易成功";
                    } else if (d.tradeStatus === '1') {
                        return "交易失败";
                    } else {
                        return "交易进行中";
                    }
                } else {
                    return "未知";
                }

            }
        }, {
            field: 'returnMsg',
            title: '回执消息',
            align: 'center',
            sort: true
        }, {
            field: 'createTime',
            title: '操作时间',
            align: 'center',
            sort: true
        }, {
            field: 'createTime',
            title: '回执时间',
            align: 'center',
            fixed: "right",
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#productTransactionTables', '/productTransaction/list', cols, table);

    // //监听工具条
    // table.on('tool(websiteBulletinTables)', function (obj) {
    //     var data = obj.data;
    //     //修改
    //     if (obj.event === 'update') {
    //         var index = layui.layer.open({
    //             title: "修改公告",
    //             type: 2,
    //             skin: '',
    //             offset: ['85px', '330px'],
    //             area: ['800px', '600px'],
    //             content: PageContext.getUrl("/websiteBulletin/toadd"),
    //             success: function (layero, index) {
    //                 var body = layui.layer.getChildFrame('body', index);
    //                 if (data) {
    //                     body.find("#id").val(data.id);
    //                     body.find("#topic").val(data.topic);
    //                     body.find("#type").val(data.type);
    //                     body.find("#content").val(data.content);
    //                     //发布状态赋值
    //                     body.find("input[name=publishStatus]").each(function (i, item) {
    //                         if ($(item).val() == data.publishStatus) {
    //                             $(item).prop('checked', true);
    //                         }
    //                     });
    //                     //是否置顶赋值
    //                     body.find("input[name=topMark]").each(function (i, item) {
    //                         if ($(item).val() == data.topMark) {
    //                             $(item).prop('checked', true);
    //                         }
    //                     });
    //                     form.render();
    //                 }
    //             }
    //         });
    //     } else if (obj.event === 'disable') {//禁用
    //         layer.confirm('真的禁用公告么？', function (index) {
    //             var ajaxReturnData;
    //             $.ajax({
    //                 url: PageContext.getUrl('/websiteBulletin/disable'),
    //                 type: 'post',
    //                 async: false,
    //                 data: {id: data.id},
    //                 success: function (data) {
    //                     ajaxReturnData = data;
    //                 }
    //             });
    //             if (ajaxReturnData.flag == 'true') {
    //                 table.reload('websiteBulletinTables');
    //                 layer.msg(ajaxReturnData.msg, {icon: 1});
    //             } else {
    //                 layer.msg('操作失败', {icon: 5});
    //             }
    //             layer.close(index);
    //         });
    //     } else if (obj.event === 'enable') {//启用
    //         layer.confirm('真的将该公告置为可用么？', function (index) {
    //             var ajaxReturnData;
    //             $.ajax({
    //                 url: PageContext.getUrl('/websiteBulletin/enable'),
    //                 type: 'post',
    //                 async: false,
    //                 data: {id: data.id},
    //                 success: function (data) {
    //                     ajaxReturnData = data;
    //                 }
    //             });
    //             if (ajaxReturnData.flag == 'true') {
    //                 table.reload('websiteBulletinTables');
    //                 layer.msg(ajaxReturnData.msg, {icon: 1});
    //             } else {
    //                 layer.msg('操作失败', {icon: 5});
    //             }
    //             layer.close(index);
    //         });
    //     } else if (obj.event === 'delete') {
    //         layer.confirm('真的删除该公告么？', function (index) {
    //             var ajaxReturnData;
    //             $.ajax({
    //                 url: PageContext.getUrl('/websiteBulletin/delete'),
    //                 type: 'post',
    //                 async: false,
    //                 data: {id: data.id},
    //                 success: function (data) {
    //                     ajaxReturnData = data;
    //                 }
    //             });
    //             //删除结果
    //             if (ajaxReturnData.flag == 'true') {
    //                 table.reload('websiteBulletinTables');
    //                 layer.msg(ajaxReturnData.msg, {icon: 1});
    //             } else {
    //                 layer.msg('删除失败', {icon: 5});
    //             }
    //             layer.close(index);
    //         });
    //     }
    // });
    //
    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        search: function () {

            var arr = [];
            $("input:checkbox[name='rtxnTypeCode']:checked").each(function (i) {
                arr = [];
                arr[i] = $(this).val();
            });
            if (arr.length > 0) {
                $("#typeCodes").val(arr.join(","));
            }

            Common.searchTable('searchForm', initTable);
        },
        searchFormClear: function () {
            Common.searchTableClear('searchForm');
        },
        add: function () {
            Common.openFrame("/websiteBulletin/toadd", "新增公告", '800px', '600px');
        },
        exportExcel: function () {
            Common.exportExcel("productTransactionTables", "产品投资记录", excel);
        }
    };
});