$(document).ready(function() {

    const areaList = JSON.parse(window.areaJsonTxt).response.body.items.item;


    $(".area").each(function(index) {
        if (areaList[index]) {
            $(this).val(areaList[index].name);
            $(this).attr("data-code", areaList[index].code);
        }
    });


    $(".area").on("click", function() {
        const areaCode = $(this).attr("data-code");
        console.log("지역코드:", areaCode);

        if (areaCode) {
            location.href = `/api/tour/areaList?areaCode=${areaCode}`;
        } else {
            alert("지역 코드가 없습니다.");
        }
    });

});
