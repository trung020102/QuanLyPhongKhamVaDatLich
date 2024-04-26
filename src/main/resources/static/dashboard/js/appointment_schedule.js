// Lấy tất cả các button mở modal bằng class

var openModalBtns = document.querySelectorAll('.openModalBtn');
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


// Lặp qua từng button và thêm sự kiện click
openModalBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModal();
    });
});

// Hàm mở modal
function openModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeModal();
    });

}



// Lấy phần tử nút đóng modal
var closeBtn = document.querySelector('.close');


// Hàm ẩn modal
function closeModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";
}
/* modal send mail*/
// Lấy tất cả các button mở modal bằng class

var openModalSendBtns = document.querySelectorAll('.openModalSendBtn');

// Lặp qua từng button và thêm sự kiện click
openModalSendBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalSend();
    });
});

function openModalSend() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeSendBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeSendModal();
    });

}
// Lấy phần tử nút đóng modal
var closeSendBtn = document.querySelector('.send-close');


// Hàm ẩn modal
function closeSendModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";
}

var openDoctorModalBtn = document.querySelectorAll('.openDoctorModalBtn');

// Lặp qua từng button và thêm sự kiện click
openDoctorModalBtn.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalDoctor();
    });
});

// Hàm mở modal
function openModalDoctor() {
    // Lấy phần tử modal
    var doctorModal = document.getElementById("doctorModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    doctorModal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeDoctorModal();
    });

}



// Lấy phần tử nút đóng modal
var closeDoctorBtn = document.querySelector('.doctorClose');


// Hàm ẩn modal
function closeDoctorModal() {
    // Lấy phần tử modal
    var doctorModal = document.getElementById("doctorModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    doctorModal.style.display = "none";
}