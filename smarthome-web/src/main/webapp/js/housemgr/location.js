/**
 * 在小区平面图上显示位置
 * 
 * @param contextPath
 * @param imgSrc
 * @param xy
 */
function showLocation(contextPath, imgSrc, xy) {
    $("#list_click").slideDown();
    if (imgSrc) {
        $("#map img").attr("src", contextPath + imgSrc);
        if (xy) {
            var xyArray = xy.split(",");
            $("#spShow").text("").css({"left":xyArray[0] + "px","top":xyArray[1] + "px"});
        } else {
            $("#spShow").text("").css({"left":"0","top":"0"});
        }
    } else {
        $("#map img").attr("src", contextPath + "/images/loading.gif");
        $("#spShow").text("No Image").css({"left":"0","top":"0"});
    }
}
