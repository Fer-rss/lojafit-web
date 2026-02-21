// Confirmação de exclusão
document.addEventListener("DOMContentLoaded", function () {
    let botoesExcluir = document.querySelectorAll(".btn-small.danger");
    botoesExcluir.forEach(function (botao) {
        botao.addEventListener("click", function (event) {
            let confirmar = confirm("Deseja realmente excluir este registro?");
            if (!confirmar) {
                event.preventDefault();
            }
        });
    });
});

// Trocado de $(document).ready para $(window).on('load')
$(window).on('load', function () {

    // --- Máscaras ---
    if ($('#cpf').length)      $('#cpf').mask('000.000.000-00');
    if ($('#telefone').length) $('#telefone').mask('(00) 00000-0000');
    if ($('#cnpj').length)     $('#cnpj').mask('00.000.000/0000-00');
    if ($('#preco').length) {
        $('#preco').mask('R$ #.##0,00', {
            reverse: true,
            clearIfNotMatch: true,
            placeholder: 'R$ 0,00'
        });
    }

    // --- Validação visual de e-mail ---
    if ($('#email').length) {
        $('#email').on('blur', function () {
            const val = $(this).val();
            const valido = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(val);
            $(this).css('border-color', val === '' || valido ? '' : 'red');
        });
    }

});
