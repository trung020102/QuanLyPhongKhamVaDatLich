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

    var closeDoctorBtn = document.querySelector('.doctorClose');

    // Gán sự kiện click cho nút đóng modal
    closeDoctorBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeDoctorModal();
    });

}

// Hàm ẩn modal
function closeDoctorModal() {
    // Lấy phần tử modal
    var doctorModal = document.getElementById("doctorModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    doctorModal.style.display = "none";
}

var openEditDoctorModalBtn = document.querySelectorAll('.openDoctorEditModal');

// Lặp qua từng button và thêm sự kiện click
openEditDoctorModalBtn.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openEditModalDoctor();
    });
});

// Hàm mở modal
function openEditModalDoctor() {
    // Lấy phần tử modal
    var doctorEditModal = document.getElementById("doctorEditModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    doctorEditModal.style.display = "block";

    var closeEditDoctorBtn = document.querySelector('.doctorEditClose');

    // Gán sự kiện click cho nút đóng modal
    closeEditDoctorBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeEditDoctorModal();
    });

}

// Hàm ẩn modal
function closeEditDoctorModal() {
    // Lấy phần tử modal
    var doctorEditModal = document.getElementById("doctorEditModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    doctorEditModal.style.display = "none";
}

//Upload avatar
var profilePic = document.getElementById("avatar");
var avatarBtn = document.getElementById("upload_image");

avatarBtn.onchange = function() {
    profilePic.src = URL.createObjectURL(avatarBtn.files[0]);
}

//Change avatar
var changeProfilePic = document.getElementById("changeAvatar");
var changeAvatarBtn = document.getElementById("change_upload_image");

changeAvatarBtn.onchange = function() {
    changeProfilePic.src = URL.createObjectURL(changeAvatarBtn.files[0]);
}