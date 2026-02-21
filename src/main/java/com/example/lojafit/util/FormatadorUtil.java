package com.example.lojafit.util;

public class FormatadorUtil {

    public static String formatarCpf(String cpf) {
        if (cpf == null || cpf.replaceAll("[^0-9]", "").length() != 11) return cpf;
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.substring(0,3) + "." + cpf.substring(3,6) + "." +
               cpf.substring(6,9) + "-" + cpf.substring(9,11);
    }

    public static String formatarTelefone(String tel) {
        if (tel == null) return tel;
        tel = tel.replaceAll("[^0-9]", "");
        if (tel.length() == 11)
            return "(" + tel.substring(0,2) + ") " + tel.substring(2,7) + "-" + tel.substring(7,11);
        if (tel.length() == 10)
            return "(" + tel.substring(0,2) + ") " + tel.substring(2,6) + "-" + tel.substring(6,10);
        return tel;
    }

    public static String formatarCnpj(String cnpj) {
        if (cnpj == null || cnpj.replaceAll("[^0-9]", "").length() != 14) return cnpj;
        cnpj = cnpj.replaceAll("[^0-9]", "");
        return cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." +
               cnpj.substring(5,8) + "/" + cnpj.substring(8,12) + "-" + cnpj.substring(12,14);
    }
}