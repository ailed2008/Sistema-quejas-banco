package cnbv;

import cnbv.dto.QuejaDTO;
import cnbv.model.QuejaBancaria;
import cnbv.service.MapeadorQuejaService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        int valor1=10, valor2 =0;
        try {
            var resultado = valor1 / valor2;
            System.out.println("resultado" +resultado);

        }catch (Exception e){
            System.out.println("Ocurrio un error: "+e);
        }

        // 1. SIMULAMOS LA LLEGADA DE UN JAVABEAN (Datos desde un formulario web)
        QuejaDTO dto = new QuejaDTO();
        dto.setFolio("QJ-2026-001");
        dto.setNumCliente("CLI-88321");
        dto.setMontoReclamado(new BigDecimal("15000.50"));
        dto.setTipoSector("BANCARIO");
        dto.setPrioridad("ALTA");
        dto.setNumeroCuenta("1234567890");
        dto.setContratoFirmadoAnexo(true); // Prueba cambiando a false para ver la excepción

        // 2. CONVERTIMOS CON EL MAPEADOR
        MapeadorQuejaService mapeador = new MapeadorQuejaService();
        QuejaBancaria queja = mapeador.convertirDTOaEntidad(dto);

        // 3. EJECUTAMOS LÓGICA DE NEGOCIO SOBRE LA ENTIDAD RECIÉN CREADA
        System.out.println("cnbv.model.Queja creada exitosamente.");
        System.out.println("Folio: " + queja.getFolio());
        System.out.println("Estado Inicial: " + queja.getEstado());
        System.out.println("Fecha Límite (SLA): " + queja.getFechaLimiteSLA());

        // Validamos requisitos normativos
        queja.validarRequisitosFormales();
        System.out.println("¡Requisitos formales aprobados correctamente!");
    }
}