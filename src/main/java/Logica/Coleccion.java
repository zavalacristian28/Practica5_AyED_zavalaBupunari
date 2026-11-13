package Logica;

public class Coleccion {
    private Archivo archivo;
    private ListaDobleCircular<Carta> LDC;
    private ListaDobleCircular<Carta> listaPorNivel;
    private ListaDobleCircular<Carta> listaPorAtaque;
    private ListaDobleCircular<Carta> listaPorDefensa;
    private ListaDobleCircular<Carta> listaPorAtributo;
    private Nodo<Carta> nodoActual;
    private ListaDobleCircular<Carta> listaActual;

    public ListaDobleCircular<Carta> getListaPorAtaque(){return listaPorAtaque;}
    public ListaDobleCircular<Carta> getLDC(){return LDC;}
    public ListaDobleCircular<Carta> getListaPorNivel(){return listaPorNivel;}
    public ListaDobleCircular<Carta> getListaPorDefensa(){return listaPorDefensa;}
    public ListaDobleCircular<Carta> getListaPorAtributo(){return listaPorAtributo;}
    public ListaDobleCircular<Carta> getListaActual(){return listaActual;}

    public Coleccion(){
        archivo=new Archivo();
        archivo.cargarArchivo();
        LDC=archivo.getLDC();
        listaPorNivel=new ListaDobleCircular<>();
        listaPorAtaque=new ListaDobleCircular<>();
        listaPorDefensa=new ListaDobleCircular<>();
        listaPorAtributo=new ListaDobleCircular<>();
        this.nodoActual = LDC != null ? LDC.getInicio() : null;
        this.listaActual=LDC;
    }
    public Archivo getArchivo(){
        return archivo;
    }

    public void moverCartas(int n){
        if(nodoActual!=null) {
            if (n == 1) {
                nodoActual = nodoActual.getSig();
            } else {
                nodoActual = nodoActual.getAnt();
            }
        }
    }

    public void ordenarNivelAsc(){
        listaPorNivel.vaciar();
        if(LDC.getInicio() == null){
            return;
        }
        Nodo<Carta> actual = LDC.getInicio();
        do {
            Carta carta = actual.getInfo();
            insertarOrdenado(carta);
            actual = actual.getSig();
        } while(actual != LDC.getInicio());
        setListaActual(listaPorNivel);
    }

    public void ordenarNivelDesc(){
        listaPorNivel.vaciar();
        if(LDC.getInicio() == null){
            return;
        }
        Nodo<Carta> actual = LDC.getInicio();
        do {
            Carta carta = actual.getInfo();
            insertarOrdenadoDesc(carta);
            actual = actual.getSig();
        } while(actual != LDC.getInicio());
        setListaActual(listaPorNivel);
    }
    //este mostrara todas cuyo nivel sea igual al parametro
    public void mostrarPorNivel(int nivel){
        listaPorNivel.vaciar();
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            compararNivel(nivel,carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorNivel);
    }
    public void mostrarPorAtributo(String atributo){
        listaPorAtributo.vaciar();
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            compararAtributo(atributo,carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorAtributo);
    }
    public void ordenarPorAtaqueAsc(){
        listaPorAtaque.vaciar();
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            insertaAtaque(carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorAtaque);
    }
    public void ordenarPorAtaqueDesc(){
        listaPorAtaque.vaciar();
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            insertaAtaqueDesc(carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorAtaque);
    }
    public void ordenarPorDefensaAsc(){
        listaPorDefensa.vaciar();
        if(LDC.getInicio() == null){
            return;
        }
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            insertaDefensa(carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorDefensa);
    }
    public void ordenarPorDefensaDesc(){
        listaPorDefensa.vaciar();
        Nodo<Carta> actual=LDC.getInicio();
        do{
            Carta carta=actual.getInfo();
            insertaDefensaDesc(carta);
            actual=actual.getSig();
        }while(actual!=LDC.getInicio());
        setListaActual(listaPorDefensa);
    }
    public void mostrarTodasLasCartas(){
        if(LDC.getInicio() == null){
            System.out.println("La lista está vacia");
            return;
        }
        Nodo<Carta> actual = LDC.getInicio();
        int cont = 1;
        do {
            cont++;
            actual = actual.getSig();

        } while(actual != LDC.getInicio());
        setListaActual(LDC);
    }

    private void insertarOrdenado(Carta carta){
        Nodo<Carta> nuevo = new Nodo<>(carta);
        if(listaPorNivel.getInicio() == null){
            listaPorNivel.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorNivel.getInicio();
        if(carta.getNivel() < actual.getInfo().getNivel()){
            listaPorNivel.insertaInicio(carta);
            return;
        }
        while(actual.getSig() != listaPorNivel.getInicio()){
            if(carta.getNivel() < actual.getSig().getInfo().getNivel()){
                break;
            }
            actual = actual.getSig();
        }
        if(actual.getSig() == listaPorNivel.getInicio()){
            listaPorNivel.insertaFin(carta);
        }else{
            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }

    private void insertarOrdenadoDesc(Carta carta){
        Nodo<Carta> nuevo = new Nodo<>(carta);

        if(listaPorNivel.getInicio() == null){
            listaPorNivel.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorNivel.getInicio();

        if(carta.getNivel() > actual.getInfo().getNivel()){
            listaPorNivel.insertaInicio(carta);
            return;
        }
        while(actual.getSig() != listaPorNivel.getInicio()){
            if(carta.getNivel() > actual.getSig().getInfo().getNivel()){
                break;
            }
            actual = actual.getSig();
        }
        if(actual.getSig() == listaPorNivel.getInicio()){
            listaPorNivel.insertaFin(carta);
        }else{
            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }

    public void compararNivel(int nivel,Carta carta){
        if(nivel==carta.getNivel()){
            listaPorNivel.insertaInicio(carta);
        }
    }
    public void compararAtributo(String atributo,Carta carta){
       if(atributo.equals(carta.getAtributo())){
        // if(carta.getAtributo().equals(atributo)){
            listaPorAtributo.insertaInicio(carta);
        }
    }

    public void insertaAtaque(Carta carta){
        Nodo<Carta> nuevo = new Nodo<>(carta);
        if(listaPorAtaque.getInicio() == null){
            listaPorAtaque.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorAtaque.getInicio();
        if(carta.getPuntosAtaque() < actual.getInfo().getPuntosAtaque()){
            listaPorAtaque.insertaInicio(carta);
            return;
        }
        while(actual.getSig() != listaPorAtaque.getInicio()){
            if(carta.getPuntosAtaque() < actual.getSig().getInfo().getPuntosAtaque()){
                break;
            }
            actual = actual.getSig();
        }

        if(actual.getSig() == listaPorAtaque.getInicio()){
            listaPorAtaque.insertaFin(carta);
        }else{
            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }

    public void insertaAtaqueDesc(Carta carta){
        Nodo<Carta> nuevo = new Nodo<>(carta);
        if(listaPorAtaque.getInicio() == null){
            listaPorAtaque.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorAtaque.getInicio();

        if(carta.getPuntosAtaque() > actual.getInfo().getPuntosAtaque()){
            listaPorAtaque.insertaInicio(carta);
            return;
        }

        while(actual.getSig() != listaPorAtaque.getInicio()){
            if(carta.getPuntosAtaque() > actual.getSig().getInfo().getPuntosAtaque()){
                break;
            }
            actual = actual.getSig();
        }

        if(actual.getSig() == listaPorAtaque.getInicio()){
            listaPorAtaque.insertaFin(carta);
        }else{

            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }

    public void insertaDefensa(Carta carta){

        Nodo<Carta> nuevo = new Nodo<>(carta);
        if(listaPorDefensa.getInicio() == null){
            listaPorDefensa.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorDefensa.getInicio();
        if(carta.getPuntosDefensa() < actual.getInfo().getPuntosDefensa()){
            listaPorDefensa.insertaInicio(carta);
            return;
        }
        while(actual.getSig() != listaPorDefensa.getInicio()){
            if(carta.getPuntosDefensa() < actual.getSig().getInfo().getPuntosDefensa()){
                break;
            }
            actual = actual.getSig();
        }
        if(actual.getSig() == listaPorDefensa.getInicio()){
            listaPorDefensa.insertaFin(carta);
        }else{

            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }

    public void insertaDefensaDesc(Carta carta){
        Nodo<Carta> nuevo = new Nodo<>(carta);

        if(listaPorDefensa.getInicio() == null){
            listaPorDefensa.insertaInicio(carta);
            return;
        }
        Nodo<Carta> actual = listaPorDefensa.getInicio();

        if(carta.getPuntosDefensa() > actual.getInfo().getPuntosDefensa()){
            listaPorDefensa.insertaInicio(carta);
            return;
        }
        while(actual.getSig() != listaPorDefensa.getInicio()){
            if(carta.getPuntosDefensa() > actual.getSig().getInfo().getPuntosDefensa()){
                break;
            }
            actual = actual.getSig();
        }
        if(actual.getSig() == listaPorDefensa.getInicio()){
            listaPorDefensa.insertaFin(carta);
        }else{
            Nodo<Carta> siguiente = actual.getSig();
            nuevo.setAnt(actual);
            nuevo.setSig(siguiente);
            actual.setSig(nuevo);
            siguiente.setAnt(nuevo);
        }
    }
    public Nodo<Carta> getNodoActual() {
        return nodoActual;
    }

    public void setNodoActual(Nodo<Carta> nodoActual) {
        this.nodoActual = nodoActual;
    }

    public void setListaActual(ListaDobleCircular<Carta> nuevaLista) {
        this.listaActual = nuevaLista;
        if (nuevaLista != null) {
            this.nodoActual = nuevaLista.getInicio();
        } else {
            this.nodoActual = null;
        }
    }

    public void ordenarPorNivel(boolean ascendente) {
        if (ascendente) {
            ordenarNivelAsc();
        } else {
            ordenarNivelDesc();
        }
    }

    public void ordenarPorAtaque(boolean ascendente) {
        if (ascendente) {
            ordenarPorAtaqueAsc();
        } else {
            ordenarPorAtaqueDesc();
        }
    }

    public void ordenarPorDefensa(boolean ascendente) {
        if (ascendente) {
            ordenarPorDefensaAsc();
        } else {
            ordenarPorDefensaDesc();
        }
    }
    public void filtrarPorNivelEspecifico(int nivel) {
        mostrarPorNivel(nivel);
    }
}
