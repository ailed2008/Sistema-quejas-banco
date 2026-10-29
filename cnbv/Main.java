package cnbv;

import cnbv.dto.QuejaDTO;
import cnbv.model.EstadoQueja;
import cnbv.model.Prioridad;
import cnbv.model.QuejaBancaria;
import cnbv.model.QuejaTarjetaCredito;
import cnbv.service.GestionQuejasService;
import cnbv.service.MapeadorQuejaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        /*
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

            */

        System.out.println("***************Iniciando sistema***************");
        //Inicializar servicio
        GestionQuejasService servicio = new GestionQuejasService();
        try {
            System.out.println("----- Registrando-------");
            QuejaTarjetaCredito quejaTC = new QuejaTarjetaCredito(
                    "TC-2026-001",
                    LocalDate.now(),
                    "CLI-98787",
                    new BigDecimal("4500.00"),
                    Prioridad.ALTA,
                    "Centro comercial Reforma, CDMX",
                    LocalDate.now().atStartOfDay().minusHours(2)
            );

            //Calcular la fecha limite del SLA
            LocalDate fechaLimiteTC = Prioridad.ALTA.calcularFechaLimite(quejaTC.getFechaRecepcion());
            System.out.println("Fecha de recepción: " + quejaTC.getFechaRecepcion());
            System.out.println("SLA Prioridad ALTA (5 días hábiles). Fecha límite: " + fechaLimiteTC);

            servicio.registrarQueja(quejaTC);

            //Crear la queja bancaria de un contrato firmado anexo
            System.out.println("\n--- Registrando Queja Bancaria ---");
            QuejaBancaria quejaBancaria = new QuejaBancaria(
                    "BAN-2026-002",
                    LocalDate.now(),
                    "CLI-12345",
                    new BigDecimal("12000.00"),
                    Prioridad.MEDIA,
                    "MX540123456789012345",
                    true // Contrato firmado
            );

            LocalDate fechaLimiteBan = Prioridad.MEDIA.calcularFechaLimite(quejaBancaria.getFechaRecepcion());
            System.out.println("SLA Prioridad MEDIA (10 días hábiles). Fecha límite: " + fechaLimiteBan);
            servicio.registrarQueja(quejaBancaria);

            //Probar la busqueda por folio
            System.out.println("\n--- Probando Búsqueda por Folio ---");
            servicio.buscarPorFolio("TC-2026-001").ifPresent(q -> {
                System.out.println("¡Encontrado! Folio: " + q.getFolio() + " | Estado inicial: " + q.getEstado());
            });

            //Avanzando el estado de la queja
            System.out.println("\n--- Avanzando el estado de la queja TC-2026-001 ---");
            servicio.avanzarEstadoQueja("TC-2026-001", EstadoQueja.EN_INVESTIGACION);

           // Filtrando quejas que se encuentren en investigación
            /*System.out.println("\n--- Filtrando quejas en estado: EN_INVESTIGACION ---");
            var enInvestigacion = servicio.filtrarPorEstado(EstadoQueja.EN_INVESTIGACION);
            for (var q : enInvestigacion) {
                System.out.println(" -> Folio activo: " + q.getFolio() + " | Cliente: " + q.getNumCliente());
            }
            */

            //Intentar registrar una queja con lugar vacío
            System.out.println("\n--- Probando Validación Defensiva (Fail-Fast) ---");
            System.out.println("Intentando registrar queja con lugar de suceso en blanco...");

            QuejaTarjetaCredito quejaInvalida = new QuejaTarjetaCredito(
                    "TC-ERR-999",
                    LocalDate.now(),
                    "CLI-00000",
                    new BigDecimal("100.00"),
                    Prioridad.BAJA,
                    "   ", // ¡Espacios en blanco! Esto debe disparar una excepción
                    LocalDateTime.now()
            );
            servicio.registrarQueja(quejaInvalida);


        }catch (Exception e){
            System.err.println("Excepcion capturada por el sistema: "+e.getMessage());

        }
    }
}