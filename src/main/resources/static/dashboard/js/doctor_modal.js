$(document).ready(function() {
    assignDataToTable();

    $('.openDoctorModalBtn').on('click', function() {
        openModalDoctor();
    });

    function openModalDoctor() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "block";

        var closeDoctorBtn = document.querySelector('.doctorClose');
        closeDoctorBtn.addEventListener('click', function() {
            closeDoctorModal();
        });
    }

    function closeDoctorModal() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "none";
    }

    $('.button-container').on('click', '#cancelBtn', function() {
        closeDoctorModal();
        document.getElementById('doctor_name').value = '';
        document.getElementById('doctor_specialty').value = '';
        document.getElementById('doctor_workplace').value = '';
        document.getElementById('doctor_diploma').value = '';
        document.getElementById('doctor_introduction').value = '';
    })

    $('.button-container').on('click', '#cancelEditBtn', function() {
        closeEditDoctorModal();
    })

    $('table').on('click', '#openDoctorEditModal', function() {
        var id = $(this).data('id');
        $('#doctor_id_edit').val(id);
        openEditModalDoctor(id);
    });

    function openEditModalDoctor(id) {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "block";

        var closeEditDoctorBtn = document.querySelector('.doctorEditClose');
        closeEditDoctorBtn.addEventListener('click', function() {
            closeEditDoctorModal();
        });

        $.ajax({
            type: "GET",
            url: "http://localhost:8082/api/doctors/get/" + id,
            success: function(data) {
                $('#doctor_name_edit').val(data.doctor_name);
                $('#doctor_specialty_edit').val(data.specialty);
                $('#doctor_workplace_edit').val(data.workplace);
                $('#doctor_diploma_edit').val(data.diploma);
                $('#doctor_introduction_edit').val(data.introduction);
                $('#changeAvatar').val(data.avatar);
            },
            error: function(err) {
                console.log("Error fetching doctor data:", err);
                alert("Error fetching doctor data: " + err.responseText);
            }
        });
    }

    function closeEditDoctorModal() {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "none";
    }

    let deleteDoctorID;

    $('table').on('click', '#delete', function() {
        deleteDoctorID = $(this).closest('tr').find('td:first').text();
        openConfirmDeleteModal();
    })

    function openConfirmDeleteModal() {
        var confirmDeleteModal = document.getElementById("confirmDeleteModal");
        confirmDeleteModal.style.display = "block";
    }

    function closeConfirmDeleteModal() {
        var confirmDeleteModal = document.getElementById("confirmDeleteModal");
        confirmDeleteModal.style.display = "none";
    }

    $('#confirmDelete').on('click', function() {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8082/api/doctors/delete/" + deleteDoctorID,
            success: function(data) {
                alert("Delete success");
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
            }
        });

        closeConfirmDeleteModal();
    });

    $('#cancelDelete').on('click', function() {
        closeConfirmDeleteModal();
    });

    $('.deleteModalClose').on('click', function() {
        closeConfirmDeleteModal();
    })

    $('#okBtn').on('click', function() {
        var name = $("#doctor_name").val();
        var specialty = $("#doctor_specialty").val();
        var workplace = $("#doctor_workplace").val();
        var diploma = $("#doctor_diploma").val();
        var introduction = $("#doctor_introduction").val();

        if (!name || !specialty || !diploma || !workplace || !introduction) {
            alert("All fields are required.");
            return;
        }

        var jsonVar = {
            doctor_name: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        $.ajax({
            type: "POST",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/create",
            success: function(data) {
                closeDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    $('#okEditBtn').on('click', function() {
        var id = $('#doctor_id_edit').val();
        var name = $("#doctor_name_edit").val();
        var specialty = $("#doctor_specialty_edit").val();
        var workplace = $("#doctor_workplace_edit").val();
        var diploma = $("#doctor_diploma_edit").val();
        var introduction = $("#doctor_introduction_edit").val();

        var jsonVar = {
            doctor_name: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        console.log("Updating doctor with ID:", id);
        console.log(JSON.stringify(jsonVar));

        $.ajax({
            type: "PUT",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/put/" + id,
            success: function(data) {
                $("#doctor_name_edit").val("");
                $("#doctor_specialty_edit").val("");
                $("#doctor_workplace_edit").val("");
                $("#doctor_diploma_edit").val("");
                $("#doctor_introduction_edit").val("");
                closeEditDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    function assignDataToTable() {
        $("tbody").empty();
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/get",
            success: function(data) {
                var doctor = JSON.parse(JSON.stringify(data));
                for (var i in doctor) {
                    $("tbody").append("<tr>" +
                        "<td>" + doctor[i].id + "</td>" +
                        "<td>" + doctor[i].doctor_name + "</td>" +
                        "<td>" + doctor[i].specialty + "</td>" +
                        "<td>" + doctor[i].diploma + "</td>" +
                        "<td>" + doctor[i].workplace + "</td>" +
                        "<td>" + doctor[i].introduction + "</td>" +
                        "<td class='button-cell'><div class='table-button-container'><button class='btn btn-primary' data-id='"
                        + doctor[i].id + "' id='openDoctorEditModal'>Edit</button>" +
                        "<button class='btn btn-danger' id='delete'>Delete</button></div></td>" +
                    "</tr>");
                }
            },
            error: function(data) {
                console.log(data);
            }
        });
    }
});
