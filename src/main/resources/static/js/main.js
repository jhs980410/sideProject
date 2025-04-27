$(".tour-list").on("click", ".tour-card", function () {
    const contentId = $(this).data("id"); // data-id 읽기
    location.href = `/api/tour/detailView?contentId=${contentId}`;
});
// 헤더 슬라이드 효과 //
$("#area-toggle-btn").click(function() {
    $("#area-list").slideToggle();
});
