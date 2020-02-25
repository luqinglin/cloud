var myScroll,pullDownEl, pullDownOffset,pullUpEl, pullUpOffset,generatedCount = 0;
var myScroll2,pullDownEl2, pullDownOffset2,pullUpEl2, pullUpOffset2,generatedCount2 = 0;

//我的直属客户
function loaded() {
	//动画部分
	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll('wrapper', {
		useTransition: true,
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl.className.match('loading')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新';
			} else if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';
			}
		},
		onScrollMove: function () {
		
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '释放刷新';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '释放刷新';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl.className.match('flip')) {
				pullDownEl.className = 'loading';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中';
                myScroll.refresh();
			} else if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';
                if($("#directlyIsFresh").val() == 0){
                    pullUpAction();	// Execute custom function (ajax call?)
                }else {
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '已是最后一页';
                }
			}
		}
	});
    //directlyUnderUser(10, 1);
}



document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);//阻止冒泡
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 0); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded2, 0); }, false);


//上拉加载更多数据
function pullUpAction () {
	setTimeout(directlyUnderUser(10, parseInt($("#directlyCountPage").val())+1), 400);
}



//我的下级客户
function loaded2() {
	//动画部分
	pullDownEl2 = document.getElementById('pullDown2');
	pullDownOffset2 = pullDownEl2.offsetHeight;
	pullUpEl2 = document.getElementById('pullUp2');	
	pullUpOffset2 = pullUpEl2.offsetHeight;
	myScroll2 = new iScroll('wrapper2', {
		useTransition: true,
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl2.className.match('loading')) {
				pullDownEl2.className = '';
				pullDownEl2.querySelector('.pullDownLabel2').innerHTML = '下拉刷新';
			} else if (pullUpEl2.className.match('loading')) {
				pullUpEl2.className = '';
				pullUpEl2.querySelector('.pullUpLabel2').innerHTML = '上拉加载更多';
			}
		},
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl2.className.match('flip')) {
				pullDownEl2.className = 'flip';
				pullDownEl2.querySelector('.pullDownLabel2').innerHTML = '释放刷新';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl2.className.match('flip')) {
				pullDownEl2.className = '';
				pullDownEl2.querySelector('.pullDownLabel2').innerHTML = 'Pull down to refresh...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl2.className.match('flip')) {
				pullUpEl2.className = 'flip';
				pullUpEl2.querySelector('.pullUpLabel2').innerHTML = '释放刷新';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl2.className.match('flip')) {
				pullUpEl2.className = '';
				pullUpEl2.querySelector('.pullUpLabel2').innerHTML = 'Pull up to load more...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl2.className.match('flip')) {
				pullDownEl2.className = 'loading';
				pullDownEl2.querySelector('.pullDownLabel2').innerHTML = '加载中';
				myScroll2.refresh();
			} else if (pullUpEl2.className.match('flip')) {
				pullUpEl2.className = 'loading';
				pullUpEl2.querySelector('.pullUpLabel2').innerHTML = '上拉加载更多';
				if($("#indirectIsFresh").val() == 0){
                    pullUpAction2();	// Execute custom function (ajax call?)
				}else {
                    pullUpEl2.querySelector('.pullUpLabel2').innerHTML = '已是最后一页';
				}
			}
		}
	});
    //indirectUser(10, 1);
}

//上拉加载更多数据
function pullUpAction2 () {
	setTimeout(indirectUser(10,parseInt($("#indirectCountPage").val())+1), 400);
}

function directlyUnderUser(rows, page) {
    var thtml = "";
    $.ajax({
        url: rootPath + "/user/directlyUnderUser",
        type: "post",
        data: {
            "pageSize": rows,
            "pageNo": page
        },
        success: function (data) {
            var d_rows = data.list;
            var pageCount = data.pageNum;
            var toalPage = data.pages;
            if(toalPage == pageCount){
                $("#directlyIsFresh").val("1");
			}
            if(d_rows != null){
                for (var j = 0; j < d_rows.length; j++) {
                    thtml += '<li><div class="memberBox">';
                    var status = "";
                    if(d_rows[j].isAgent == 1){
                        status = "一级代理";
                    }else if(d_rows[j].isAgent == 2){
                        status = "二级代理";
                    }else if(d_rows[j].isAgent == 3){
                        status = "三级代理";
                    }else {
                        status = "普通会员";
                    }
                    thtml += '<dl> <dd>会员昵称</dd> <dt>'+d_rows[j].name+'</dt> </dl>';
                    thtml += '<dl> <dd>注册日期</dd> <dt>'+dateTimeFormatter(d_rows[j].ctime*1000)+'</dt> </dl>';
                    thtml += '<dl> <dd>会员权限</dd> <dt>'+status+'</dt> </dl>';
                    thtml += '</div></li>';
                }
            }
            $("#directlyUnderUser").append(thtml);
            myScroll.refresh();
            $("#directlyCountPage").val(pageCount);
        }
    });
}
function indirectUser(rows, page) {
    var thtml = "";
    $.ajax({
        url: rootPath + "/user/indirectUser",
        type: "post",
        data: {
            "pageSize": rows,
            "pageNo": page
        },
        success: function (data) {
            var d_rows = data.list;
            var pageCount = data.pageNum;
            var toalPage = data.pages;
            if(toalPage == pageCount){
                $("#indirectIsFresh").val("1");
            }
            if(d_rows != null){
                for (var j = 0; j < d_rows.length; j++) {
                    thtml += '<li><div class="memberBox">';
                    var status = "";
                    if(d_rows[j].isAgent == 1){
                        status = "一级代理";
                    }else if(d_rows[j].isAgent == 2){
                        status = "二级代理";
                    }else if(d_rows[j].isAgent == 3){
                        status = "三级代理";
                    }else {
                        status = "普通会员";
                    }
                    thtml += '<dl> <dd>会员昵称</dd> <dt>'+d_rows[j].name+'</dt> </dl>';
                    thtml += '<dl> <dd>注册日期</dd> <dt>'+dateTimeFormatter(d_rows[j].ctime*1000)+'</dt> </dl>';
                    thtml += '<dl> <dd>会员权限</dd> <dt>'+status+'</dt> </dl>';
                    thtml += '</div></li>';
                }
            }
            $("#indirectUser").append(thtml);
            myScroll2.refresh();
            $("#indirectCountPage").val(pageCount);
        }
    });
}

