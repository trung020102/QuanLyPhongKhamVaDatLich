$(function () {
    $('#example2').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "responsive": true,
    });
    $('.selectpicker').selectpicker();
    DocterCreate.init();
});

const DocterCreate = (function () {
    const module = {
        createDoctorUrl: '/api/doctor',
        modalEditDoctor: $('#serviceEditModal'),
        doctorNameSelector: $('#doctor_name'),
        doctorSpecialtySelector: $('#doctor_specialty'),
        doctorWorkplaceSelector: $('#doctor_workplace'),
        doctorIntroductionSelector: $('#doctor_introduction'),
        doctorDiplomaSelector: $('#doctor_diploma'),
        createBtnSelector: $('#create_Doctor_Btn')
    }
    module.init = () => {
        handleCreateDoctor();
    }
    const openEditServiceModalButton = () => {

    }
    const handleCreateDoctor = () => {
        module.createBtnSelector.on('click', function () {
            const doctorResister = {
                username: module.doctorNameSelector.val().trim(),
                specialty: module.doctorSpecialtySelector.val().trim(),
                diploma: module.doctorDiplomaSelector.val().trim(),
                workplace: module.doctorWorkplaceSelector.val().trim(),
                introduction: module.doctorIntroductionSelector.val().trim(),
            };

            $.ajax({
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                type: 'POST',
                url: module.createDoctorUrl,
                data: JSON.stringify(doctorResister),
            })
                .done(() => {
                    alert("Tạo bác sĩ thành công")
                    window.location.href = "/admin/doctor";
                })
                .fail((jqXhr) => {
                    console.log(jqXhr)
                })
        })
    }
    return module;
})();