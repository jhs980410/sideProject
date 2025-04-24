$(".tour-list").on("click", ".tour-card", function () {
    const contentId = $(this).data("id"); // data-id 읽기
    location.href = `/api/tour/detailView?contentId=${contentId}`;
});

