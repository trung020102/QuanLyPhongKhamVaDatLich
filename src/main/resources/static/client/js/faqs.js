  $(document).ready(function () {
        $(".collapse").on("show.bs.collapse", function () {
          $(this).siblings(".card-header").addClass("bg-card-header");
          $(this).siblings(".card-header").find("h5").addClass("text-white");
          $(this).addClass("border-card-box");
          $(this).siblings(".card-header").find(".chev").html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708z"/></svg>')
        });

        $(".collapse").on("hide.bs.collapse", function () {
          $(this).siblings(".card-header").removeClass("bg-card-header");
          $(this).siblings(".card-header").find("h5").removeClass("text-white");
          $(this).removeClass("border-card-box");
          $(this).siblings(".card-header").find(".chev").html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708"/></svg>')
        });
  });