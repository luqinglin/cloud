// JavaScript Document
//rem自适应字体大小方法
var docEl = document.documentElement,
resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
recalc = function() {
    //设置根字体大小
    docEl.style.fontSize = 10 * (docEl.clientWidth / 320) + 'px';
};
//绑定浏览器缩放与加载时间
window.addEventListener(resizeEvt, recalc, false);
document.addEventListener('DOMContentLoaded', recalc, false);

//底部导航栏
function navTab(el) {
   el.children("span").addClass("checked").siblings("p").addClass("pjBlue");
   el.siblings().children("span").removeClass("checked").siblings("p").removeClass("pjBlue");
}
$("footer li").on("touchend",function () {
    navTab($(this));
})

//协议勾选
// $(".per_uncheck").on("touchend",function(e) {
// 	e.stopPropagation();
// 	e.preventDefault(); //阻止浏览器元素默认行为
// 	$(this).toggleClass("per_checked");
// });
