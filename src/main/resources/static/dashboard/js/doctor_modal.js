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

    // Lấy nút đóng modal
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

$(document).ready(function(){
    assignDataToTable();

    $('table').on('click', 'button[id="delete"]', function(e){
        var id = $(this).closest('tr').children('td:first').text();
        $.ajax({
            type:"DELETE",
            url:"http://localhost:8082/api/doctors/"+id,
            success: function(data){
                alert("Delete success", true);
                assignDataToTable();
            },
            error: function(err){
                console.log(err);
                alert(err);
            }
        });
    });

    $('table').on('click', 'button[id="edit"]', function(e){
        var id = $(this).closest('tr').children('td:first').text();
        var name = $(this).closest('tr').children('td:nth-child(2)').text();
        var specialty = $(this).closest('tr').children('td:nth-child(3)').text();
        var diploma = $(this).closest('tr').children('td:nth-child(4)').text();
        var workplace = $(this).closest('tr').children('td:nth-child(5)').text();
        var introduction = $(this).closest('tr').children('td:nth-child(6)').text();

        $("#doctor_name").val(name);
        $("#doctor_specialty").val(specialty);
        $("#doctor_diploma").val(diploma);
        $("#doctor_workplace").val(workplace);
        $("#doctor_introduction").val(introduction);

        $("#okeEditBtn").click(function(){
            var jsonVar = {
                name: $("#doctor_name").val(),
                specialty: $("#doctor_specialty").val(),
                diploma: $("#doctor_diploma").val(),
                workplace: $("#doctor_workplace").val(),
                introduction: $("#doctor_introduction").val()
            };

            $.ajax({
                type:"PUT",
                data: JSON.stringify(jsonVar),
                contentType:"application/json",
                url:"http://localhost:8082/api/doctors/"+id,

                success: function(data){
                    alert("Update success", true);
                    $("#doctor_name").val("");
                    $("#doctor_specialty").val("");
                    $("#doctor_diploma").val("");
                    $("#doctor_workplace").val("");
                    $("#doctor_introduction").val("");
                    assignDataToTable();
                },
                error: function(err) {
                    console.log(err);
                    alert(err);
                }
            });
        });

        $("#okBtn").click(function(){
            var jsonVar = {
                name: $("#doctor_name").val(),
                specialty: $("#doctor_specialty").val(),
                diploma: $("#doctor_diploma").val(),
                workplace: $("#doctor_workplace").val,
                introduction: $("#doctor_introduction").val()
            };

            $.ajax({
                type:"POST",
                data: JSON.stringify(jsonVar),
                url:"http://localhost:8082/api/doctors",
                contentType:"application/json",

                success: function(data){
                    assignDataToTable();
                },
                error: function(err){
                    console.log(err);
                    alert(err);
                }
            });
        });
    });

    function assignDataToTable() {
        $("tbody").empty();
        $.ajax({
            type:"GET",
            contentType:"application/json",
            url:"http://localhost:8082/api/doctors",

            success:function(data){
                var doctor = JSON.parse(JSON.stringify(data));
                for(var i in doctor){
                    $("tbody").append("<tr>" +
                        "<td>" + doctor[i].id + "</td>" +
                        "<td>" + doctor[i].name + "</td>" +
                        "<td>" + doctor[i].specialty+ "</td>" +
                        "<td>" + doctor[i].diploma + "</td>" +
                        "<td>" + doctor[i].workplace + "</td>" +
                        "<td>" + doctor[i].introduction + "</td>" +
                        "<td><button class='btn btn-primary' onclick='editDoctor(" + doctor[i].id + ")' id='doctorEditModal'>Edit</button>" +
                        "<button class='btn btn-danger' id='delete'>Delete</button></td>" +
                    "</tr>");
                }
            },
            error: function(data){
                console.log(data);
            }
        });
    }
});