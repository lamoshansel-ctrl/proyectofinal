
package ejemploproyectofinal;

import java.util.ArrayList;
import java.util.Scanner;

public class FerrocarrilAntioquia {

    static Scanner sc = new Scanner(System.in);

    // Listas del sistema
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Cajero> cajeros = new ArrayList<>();
    static ArrayList<Tren> trenes = new ArrayList<>();
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static ArrayList<Venta> ventas = new ArrayList<>();

    // Clases de las entidades

    static class Usuario {
        int id;
        String nombre, apellido, origen, destino;

        Usuario(int id, String nombre, String apellido,
                String origen, String destino) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.origen = origen;
            this.destino = destino;
        }
    }

    static class Cajero {
        int id;
        String nombre, apellido, rol, password;

        Cajero(int id, String nombre, String apellido,
               String rol, String password) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.rol = rol;
            this.password = password;
        }
    }

    static class Tren {
        int id;
        String modelo;
        int capacidad;

        Tren(int id, String modelo, int capacidad) {
            this.id = id;
            this.modelo = modelo;
            this.capacidad = capacidad;
        }
    }

    static class Ticket {
        int id, idUsuario, idTren;
        String silla, origen, destino, fecha, hora;
        double precio;

        Ticket(int id, int idUsuario, int idTren,
               String silla, String origen, String destino,
               String fecha, String hora, double precio) {
            this.id = id;
            this.idUsuario = idUsuario;
            this.idTren = idTren;
            this.silla = silla;
            this.origen = origen;
            this.destino = destino;
            this.fecha = fecha;
            this.hora = hora;
            this.precio = precio;
        }
    }

    static class Venta {
        int id, idTicket, cantidad;
        double precio, total;

        Venta(int id, int idTicket, int cantidad, double precio) {
            this.id = id;
            this.idTicket = idTicket;
            this.cantidad = cantidad;
            this.precio = precio;
            this.total = cantidad * precio;
        }
    }

    // Método principal

    public static void main(String[] args) {

        // Crear los tres trenes con capacidad de 4 pasajeros
        trenes.add(new Tren(1, "Tren Antioquia 1", 4));
        trenes.add(new Tren(2, "Tren Antioquia 2", 4));
        trenes.add(new Tren(3, "Tren Antioquia 3", 4));

        int opcion;

        do {
            System.out.println("\n===== FERROCARRILES DE ANTIOQUIA =====");
            System.out.println("1. Registrar cajero");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Salir");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarCajero();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 3);
    }

    // Registrar cajero

    public static void registrarCajero() {

        System.out.println("\n--- REGISTRAR CAJERO ---");

        int id = leerEntero("Ingrese ID: ");

        if (buscarCajero(id) != null) {
            System.out.println("Ese ID ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Rol: ");
        String rol = sc.nextLine();

        System.out.print("Contrasena: ");
        String password = sc.nextLine();

        cajeros.add(new Cajero(id, nombre, apellido, rol, password));

        System.out.println("Cajero registrado correctamente.");
    }

    // Buscar cajero

    public static Cajero buscarCajero(int id) {

        for (Cajero c : cajeros) {
            if (c.id == id) {
                return c;
            }
        }

        return null;
    }

    // Iniciar sesion

    public static void iniciarSesion() {

        System.out.println("\n--- INICIAR SESION ---");

        int id = leerEntero("Ingrese ID: ");

        System.out.print("Contrasena: ");
        String password = sc.nextLine();

        Cajero cajero = buscarCajero(id);

        if (cajero != null && cajero.password.equals(password)) {
            System.out.println("Bienvenido " + cajero.nombre);
            menuPrincipal();
        } else {
            System.out.println("Usuario o contrasena incorrectos.");
        }
    }

    // Dashboard

    public static void menuPrincipal() {

        int opcion;

        do {
            System.out.println("\n===== DASHBOARD =====");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Gestionar trenes");
            System.out.println("3. Gestionar tickets");
            System.out.println("4. Gestionar ventas");
            System.out.println("5. Cerrar sesion");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuTrenes();
                    break;
                case 3:
                    menuTickets();
                    break;
                case 4:
                    menuVentas();
                    break;
                case 5:
                    System.out.println("Sesion cerrada.");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 5);
    }

    // Menu de usuarios

    public static void menuUsuarios() {

        int opcion;

        do {
            System.out.println("\n--- MENU USUARIOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Consultar todos");
            System.out.println("3. Consultar por ID");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Volver");

            opcion = leerEntero("Seleccione: ");

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    consultarUsuarios();
                    break;
                case 3:
                    consultarUsuarioPorId();
                    break;
                case 4:
                    actualizarUsuario();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
            }

        } while (opcion != 6);
    }

    // Registrar usuario

    public static void crearUsuario() {

        System.out.println("\n--- CREAR USUARIO ---");

        int id = leerEntero("ID: ");

        if (buscarUsuario(id) != null) {
            System.out.println("El ID ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Origen: ");
        String origen = sc.nextLine();

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        usuarios.add(new Usuario(id, nombre, apellido, origen, destino));

        System.out.println("Usuario registrado correctamente.");
    }

    // Buscar usuario por ID

    public static Usuario buscarUsuario(int id) {

        for (Usuario u : usuarios) {
            if (u.id == id) {
                return u;
            }
        }

        return null;
    }

    // Consultar todos los usuarios

    public static void consultarUsuarios() {

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        for (Usuario u : usuarios) {
            System.out.println("\nID: " + u.id);
            System.out.println("Nombre: " + u.nombre);
            System.out.println("Apellido: " + u.apellido);
            System.out.println("Origen: " + u.origen);
            System.out.println("Destino: " + u.destino);
        }
    }

    // Consultar usuario por ID

    public static void consultarUsuarioPorId() {

        int id = leerEntero("Ingrese el ID del usuario: ");

        Usuario u = buscarUsuario(id);

        if (u != null) {
            System.out.println("Nombre: " + u.nombre);
            System.out.println("Apellido: " + u.apellido);
            System.out.println("Origen: " + u.origen);
            System.out.println("Destino: " + u.destino);
        } else {
            System.out.println("Usuario no encontrado.");
        }

    }

        public static void actualizarUsuario() {

            int id = leerEntero("Ingrese ID del usuario: ");

            Usuario u = buscarUsuario(id);

            if (u != null) {
                System.out.print("Nuevo nombre: ");
                u.nombre = sc.nextLine();

                System.out.print("Nuevo apellido: ");
                u.apellido = sc.nextLine();

                System.out.print("Nuevo origen: ");
                u.origen = sc.nextLine();

                System.out.print("Nuevo destino: ");
                u.destino = sc.nextLine();

                System.out.println("Usuario actualizado.");
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        // Eliminar usuario

        public static void eliminarUsuario() {

            int id = leerEntero("Ingrese ID: ");

            Usuario u = buscarUsuario(id);

            if (u != null) {
                usuarios.remove(u);
                System.out.println("Usuario eliminado.");
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        // ==============================
        // MENU DE TRENES
        // ==============================

        public static void menuTrenes() {

            int opcion;

            do {
                System.out.println("\n--- MENU TRENES ---");
                System.out.println("1. Registrar tren");
                System.out.println("2. Consultar todos");
                System.out.println("3. Consultar por ID");
                System.out.println("4. Actualizar tren");
                System.out.println("5. Eliminar tren");
                System.out.println("6. Volver");

                opcion = leerEntero("Seleccione: ");

                switch (opcion) {
                    case 1: crearTren(); break;
                    case 2: consultarTrenes(); break;
                    case 3: consultarTrenPorId(); break;
                    case 4: actualizarTren(); break;
                    case 5: eliminarTren(); break;
                }

            } while (opcion != 6);
        }

        public static void crearTren() {

            if (trenes.size() >= 3) {
                System.out.println("La empresa solamente cuenta con 3 trenes.");
                return;
            }

            int id = leerEntero("Numero de maquina: ");

            if (buscarTren(id) != null) {
                System.out.println("Ese tren ya existe.");
                return;
            }

            System.out.print("Modelo: ");
            String modelo = sc.nextLine();

            int capacidad = leerEntero("Capacidad (maximo 4): ");

            if (capacidad < 1 || capacidad > 4) {
                System.out.println("La capacidad debe estar entre 1 y 4.");
                return;
            }

            trenes.add(new Tren(id, modelo, capacidad));

            System.out.println("Tren registrado.");
        }

        public static Tren buscarTren(int id) {

            for (Tren t : trenes) {
                if (t.id == id) {
                    return t;
                }
            }

            return null;
        }

        public static void consultarTrenes() {

            for (Tren t : trenes) {
                System.out.println("\nID: " + t.id);
                System.out.println("Modelo: " + t.modelo);
                System.out.println("Capacidad: " + t.capacidad);
            }
        }

        public static void consultarTrenPorId() {

            int id = leerEntero("Ingrese ID del tren: ");

            Tren t = buscarTren(id);

            if (t != null) {
                System.out.println("ID: " + t.id);
                System.out.println("Modelo: " + t.modelo);
                System.out.println("Capacidad: " + t.capacidad);
            } else {
                System.out.println("Tren no encontrado.");
            }
        }

        public static void actualizarTren() {

            int id = leerEntero("Ingrese ID del tren: ");

            Tren t = buscarTren(id);

            if (t != null) {
                System.out.print("Nuevo modelo: ");
                t.modelo = sc.nextLine();

                int capacidad = leerEntero("Nueva capacidad (1 a 4): ");

                if (capacidad >= 1 && capacidad <= 4) {
                    t.capacidad = capacidad;
                    System.out.println("Tren actualizado.");
                } else {
                    System.out.println("Capacidad incorrecta.");
                }
            } else {
                System.out.println("Tren no encontrado.");
            }
        }

        public static void eliminarTren() {

            int id = leerEntero("Ingrese ID del tren: ");

            Tren t = buscarTren(id);

            if (t != null) {
                trenes.remove(t);
                System.out.println("Tren eliminado.");
            } else {
                System.out.println("Tren no encontrado.");
            }
        }

        // ==============================
        // MENU DE TICKETS
        // ==============================

        public static void menuTickets() {

            int opcion;

            do {
                System.out.println("\n--- MENU TICKETS ---");
                System.out.println("1. Crear ticket");
                System.out.println("2. Consultar todos");
                System.out.println("3. Consultar por ID");
                System.out.println("4. Actualizar ticket");
                System.out.println("5. Eliminar ticket");
                System.out.println("6. Volver");

                opcion = leerEntero("Seleccione: ");

                switch (opcion) {
                    case 1: crearTicket(); break;
                    case 2: consultarTickets(); break;
                    case 3: consultarTicketPorId(); break;
                    case 4: actualizarTicket(); break;
                    case 5: eliminarTicket(); break;
                }

            } while (opcion != 6);
        }

        public static void crearTicket() {

            int id = leerEntero("ID del ticket: ");

            if (buscarTicket(id) != null) {
                System.out.println("El ticket ya existe.");
                return;
            }

            int idUsuario = leerEntero("ID del usuario: ");

            if (buscarUsuario(idUsuario) == null) {
                System.out.println("El usuario no existe.");
                return;
            }

            int idTren = leerEntero("ID del tren: ");

            Tren t = buscarTren(idTren);

            if (t == null) {
                System.out.println("El tren no existe.");
                return;
            }

            int ocupados = 0;

            for (Ticket tk : tickets) {
                if (tk.idTren == idTren) {
                    ocupados++;
                }
            }

            if (ocupados >= t.capacidad) {
                System.out.println("El tren no tiene asientos disponibles.");
                return;
            }

            System.out.print("Numero de asiento: ");
            String silla = sc.nextLine();

            for (Ticket tk : tickets) {
                if (tk.idTren == idTren && tk.silla.equals(silla)) {
                    System.out.println("Ese asiento ya esta ocupado.");
                    return;
                }
            }

            System.out.print("Origen: ");
            String origen = sc.nextLine();

            System.out.print("Destino (Santa Fe, Cisneros o Puerto Berrio o medellin o itagui o bello): ");
            String destino = sc.nextLine();

            System.out.print("Fecha: ");
            String fecha = sc.nextLine();

            System.out.print("Hora: ");
            String hora = sc.nextLine();

            double precio = leerDouble("Precio: ");

            if (precio <= 0) {
                System.out.println("El precio debe ser mayor que cero.");
                return;
            }

            tickets.add(new Ticket(id, idUsuario, idTren,
                    silla, origen, destino, fecha, hora, precio));

            System.out.println("Ticket creado correctamente.");
        }

        public static Ticket buscarTicket(int id) {

            for (Ticket t : tickets) {
                if (t.id == id) {
                    return t;
                }
            }

            return null;
        }

        public static void consultarTickets() {

            for (Ticket t : tickets) {
                mostrarTicket(t);
            }
        }

        public static void mostrarTicket(Ticket t) {

            System.out.println("\nID: " + t.id);
            System.out.println("Usuario: " + t.idUsuario);
            System.out.println("Tren: " + t.idTren);
            System.out.println("Asiento: " + t.silla);
            System.out.println("Origen: " + t.origen);
            System.out.println("Destino: " + t.destino);
            System.out.println("Fecha: " + t.fecha);
            System.out.println("Hora: " + t.hora);
            System.out.println("Precio: " + t.precio);
        }

        public static void consultarTicketPorId() {

            int id = leerEntero("Ingrese ID del ticket: ");

            Ticket t = buscarTicket(id);

            if (t != null) {
                mostrarTicket(t);
            } else {
                System.out.println("Ticket no encontrado.");
            }
        }

        public static void actualizarTicket() {

            int id = leerEntero("Ingrese ID del ticket: ");

            Ticket t = buscarTicket(id);

            if (t != null) {

                System.out.print("Nuevo origen: ");
                t.origen = sc.nextLine();

                System.out.print("Nuevo destino: ");
                t.destino = sc.nextLine();

                System.out.print("Nueva fecha: ");
                t.fecha = sc.nextLine();

                System.out.print("Nueva hora: ");
                t.hora = sc.nextLine();

                double precio = leerDouble("Nuevo precio: ");

                if (precio > 0) {
                    t.precio = precio;
                    System.out.println("Ticket actualizado.");
                } else {
                    System.out.println("Precio incorrecto.");
                }

            } else {
                System.out.println("Ticket no encontrado.");
            }
        }

        public static void eliminarTicket() {

            int id = leerEntero("Ingrese ID del ticket: ");

            Ticket t = buscarTicket(id);

            if (t != null) {
                tickets.remove(t);
                System.out.println("Ticket eliminado.");
            } else {
                System.out.println("Ticket no encontrado.");
            }
        }

        // ==============================
        // MENU DE VENTAS
        // ==============================

        public static void menuVentas() {

            int opcion;

            do {
                System.out.println("\n--- MENU VENTAS ---");
                System.out.println("1. Registrar venta");
                System.out.println("2. Consultar todas");
                System.out.println("3. Consultar por ID");
                System.out.println("4. Actualizar venta");
                System.out.println("5. Eliminar venta");
                System.out.println("6. Volver");

                opcion = leerEntero("Seleccione: ");

                switch (opcion) {
                    case 1: crearVenta(); break;
                    case 2: consultarVentas(); break;
                    case 3: consultarVentaPorId(); break;
                    case 4: actualizarVenta(); break;
                    case 5: eliminarVenta(); break;
                }

            } while (opcion != 6);
        }

        public static void crearVenta() {

            int id = leerEntero("ID de la venta: ");

            if (buscarVenta(id) != null) {
                System.out.println("La venta ya existe.");
                return;
            }

            int idTicket = leerEntero("ID del ticket: ");

            Ticket t = buscarTicket(idTicket);

            if (t == null) {
                System.out.println("El ticket no existe.");
                return;
            }

            for (Venta v : ventas) {
                if (v.idTicket == idTicket) {
                    System.out.println("Este ticket ya tiene una venta.");
                    return;
                }
            }

            int cantidad = leerEntero("Cantidad: ");

            if (cantidad <= 0) {
                System.out.println("Cantidad incorrecta.");
                return;
            }

            double precio = t.precio;

            Venta v = new Venta(id, idTicket, cantidad, precio);

            ventas.add(v);

            System.out.println("Venta registrada.");
            System.out.println("Total: " + v.total);
        }

        public static Venta buscarVenta(int id) {

            for (Venta v : ventas) {
                if (v.id == id) {
                    return v;
                }
            }

            return null;
        }

        public static void consultarVentas() {

            for (Venta v : ventas) {
                mostrarVenta(v);
            }
        }

        public static void mostrarVenta(Venta v) {

            System.out.println("\nID venta: " + v.id);
            System.out.println("ID ticket: " + v.idTicket);
            System.out.println("Cantidad: " + v.cantidad);
            System.out.println("Precio unitario: " + v.precio);
            System.out.println("Total: " + v.total);
        }

        public static void consultarVentaPorId() {

            int id = leerEntero("Ingrese ID de venta: ");

            Venta v = buscarVenta(id);

            if (v != null) {
                mostrarVenta(v);
            } else {
                System.out.println("Venta no encontrada.");
            }
        }

        public static void actualizarVenta() {

            int id = leerEntero("Ingrese ID de venta: ");

            Venta v = buscarVenta(id);

            if (v != null) {

                int cantidad = leerEntero("Nueva cantidad: ");

                if (cantidad > 0) {
                    v.cantidad = cantidad;
                    v.total = v.cantidad * v.precio;

                    System.out.println("Venta actualizada.");
                    System.out.println("Nuevo total: " + v.total);
                } else {
                    System.out.println("Cantidad incorrecta.");
                }

            } else {
                System.out.println("Venta no encontrada.");
            }
        }

        public static void eliminarVenta() {

            int id = leerEntero("Ingrese ID de venta: ");

            Venta v = buscarVenta(id);

            if (v != null) {
                ventas.remove(v);
                System.out.println("Venta eliminada.");
            } else {
                System.out.println("Venta no encontrada.");
            }
        }

        // ==============================
        // METODOS PARA LEER DATOS
        // ==============================

        public static int leerEntero(String mensaje) {

            while (true) {
                try {
                    System.out.print(mensaje);
                    int numero = Integer.parseInt(sc.nextLine());
                    return numero;
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un numero entero.");
                }
            }
        }

        public static double leerDouble(String mensaje) {

            while (true) {
                try {
                    System.out.print(mensaje);
                    double numero = Double.parseDouble(
                            sc.nextLine().replace(",", "."));
                    return numero;
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un numero valido.");
                }
            }
        }

    }



