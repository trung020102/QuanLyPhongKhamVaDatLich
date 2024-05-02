var openServiceModalBtn = document.querySelectorAll('.openServiceModalBtn');

// Lặp qua từng button và thêm sự kiện click
openServiceModalBtn.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalService();
    });
});

// Hàm mở modal
function openModalService() {
    // Lấy phần tử modal
    var serviceModal = document.getElementById("serviceModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    serviceModal.style.display = "block";

    var closeServiceBtn = document.querySelector('.serviceClose');

    // Gán sự kiện click cho nút đóng modal
    closeServiceBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeServiceModal();
    });

}

// Hàm ẩn modal
function closeServiceModal() {
    // Lấy phần tử modal
    var serviceModal = document.getElementById("serviceModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    serviceModal.style.display = "none";
}

var openEditServiceModalBtn = document.querySelectorAll('.openEditService');

// Lặp qua từng button và thêm sự kiện click
openEditServiceModalBtn.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openEditModalService();
    });
});

// Hàm mở modal
function openEditModalService() {
    // Lấy phần tử modal
    var serviceEditModal = document.getElementById("serviceEditModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    serviceEditModal.style.display = "block";

    var closeEditServiceBtn = document.querySelector('.serviceEditClose');

    // Gán sự kiện click cho nút đóng modal
    closeEditServiceBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeEditServiceModal();
    });

}

// Hàm ẩn modal
function closeEditServiceModal() {
    // Lấy phần tử modal
    var serviceEditModal = document.getElementById("serviceEditModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    serviceEditModal.style.display = "none";
}

$("#okServiceBtn").on('click', function () {
    const serviceParam = {
        serviceName: $('#service_name').val().trim(),
        price: $('#service_price').val().trim(),
        description: $('#service_desciption').val().trim(),
    }

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: '/api/medical-service',
        data: JSON.stringify(serviceParam),
    })
        .done(() => {
            alert('OK');
        })
});


