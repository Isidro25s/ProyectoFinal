package app.valorant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.valorant.data.DAO.InfoDAO
import app.valorant.data.entities.Agente
import app.valorant.data.entities.Arma
import app.valorant.data.entities.Mapa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Agente::class, Mapa::class, Arma::class], version = 1)
abstract class InfoRoomDatabase : RoomDatabase() {

    abstract fun infoDao(): InfoDAO

    companion object {
        @Volatile
        private var INSTANCE: InfoRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): InfoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InfoRoomDatabase::class.java,
                    "info_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(InfoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class InfoDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.infoDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(infoDao: InfoDAO) {

            infoDao.deleteAllAgentes()

            var agente = Agente(
                name = "Brimstone",
                imagen = "c_brimstone",
                rol = "CONTROLADOR",
                rol_icon = "r_controller",
                bio = "Proveniente de estados unidos, Brimstone proporciona una constante ventaja para su equipo con su arsenal orbital. Su habilidad otorga información de forma precisa y a distancia, lo que lo hace un comandante sin igual en el campo.",
                icon_q = "c_breach_q",
                nombre_q = "Incendiaria",
                desc_q = "Equipa un lanzagranadas incendiario y dispara para lanzar una granada que explota cuando se detiene en el suelo. Esta crea una zona de fuego que permanece en el campo y daña a los enemigos que estén dentro de ella.",
                icon_e = "c_breach_e",
                nombre_e = "Humo celestial",
                desc_e = "Equipa un mapa estratégico y dispara para marcar las ubicaciones en las que caerán las nubes de humo de brimstone. Usa el disparo secundario para confirmar y lanzar nubes de humo de larga duración que bloquearán la visión en la zona seleccionada.",
                icon_c = "c_breach_c",
                nombre_c = "Baliza potenciadora",
                desc_c = "Equipa una baliza potenciadora y dispara para lanzarla frente a brimstone. Al caer, resguardo potenciador creará un campo que aumenta la velocidad de disparo de los jugadores.",
                icon_x = "c_breach_x",
                nombre_x = "Ataque orbital",
                desc_x = "Equipa un mapa estratégico y dispara para lanzar un ataque orbital prolongado de un láser en la ubicación seleccionada. Este infligirá daño masivo con el tiempo a los jugadores que estén en la zona."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Phoenix",
                imagen = "c_phoenix",
                rol = "DUELISTA",
                rol_icon = "r_duelist",
                bio = "Proviene del reino unido. El poder estelar de Phoenix se manifiesta en su estilo de combate al incendiar el campo de batalla con sus granadas aturdidoras y cegadoras. Sin importar si cuenta con apoyo o no, participará en la batalla bajo sus propios términos.",
                icon_q = "c_phoenix_q",
                nombre_q = "Destello en curva",
                desc_q = "Equipa un orbe cegador que recorre una trayectoria curva y detona poco después de lanzarlo. Dispara para cambiar la trayectoria del orbe cegador hacia la izquierda. Este detonará y cegará a cualquier jugador que lo vea. Usa el disparo secundario para cambiar su trayectoria hacia la derecha.",
                icon_e = "c_phoenix_e",
                nombre_e = "Manitas calientes",
                desc_e = "Equipa una bola de fuego y dispara para lanzarla. Esta explotará tras cierto tiempo o al impactar el suelo, lo que creará una zona de fuego que permanecerá en el campo y dañará a los enemigos.",
                icon_c = "c_phoenix_c",
                nombre_c = "Muro abrasador",
                desc_c = "Equipa un muro de fuego y dispara para crear una hilera de llamas que avanza, lo que creará un muro que bloquea la visión y dañará a los jugadores que lo atraviesen. Mantén presionado el botón de disparo para mover el muro en la dirección de tu retícula.",
                icon_x = "c_phoenix_x",
                nombre_x = "Va de fuego",
                desc_x = "Coloca al instante un marcador en la ubicación de phoenix. Cuando esta habilidad esté activa, morir o dejar que el temporizador expire hará que la habilidad se termine y regresará a phoenix a esa ubicación con toda su vida."

            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Sage",
                imagen = "c_sage",
                rol = "CENTINELA",
                rol_icon = "r_sentinel",
                bio = "El bastión de china. Sage proporciona seguridad para sí misma y para su equipo en cualquier lugar. Gracias a su capacidad de revivir a sus compañeros caídos y evitar ataques agresivos, les da un lugar de protección en medio de la caótica pelea.",
                icon_q = "c_sage_q",
                nombre_q = "Orbe ralentizador",
                desc_q = "Equipa un orbe de ralentización y dispara para lanzarlo. Este detonará al caer al suelo y creará un campo que permanecerá en la zona y ralentizará a los jugadores que estén dentro de él.",
                icon_e = "c_sage_e",
                nombre_e = "Orbe curativo",
                desc_e = "Equipa un orbe curativo y dispara tras apuntar a un aliado herido para curarlo con el tiempo. Usa el disparo secundario cuando sage esté herida para curarla con el tiempo.",
                icon_c = "c_sage_c",
                nombre_c = "Orbe de barrera",
                desc_c = "Equipa un orbe de barrera.Dispara para colocar una pared sólida. Usa el disparo secundario para rotarla antes de crearla.",
                icon_x = "c_sage_x",
                nombre_x = "Resurrección",
                desc_x = "Equipa una habilidad de resurrección y dispara mientras apuntas hacia un aliado muerto para comenzar a resucitarlo.Tras un breve periodo de canalización, el aliado revivirá con toda su vida. "
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Sova",
                imagen = "c_sova",
                rol = "INICIADOR",
                rol_icon = "r_initiator",
                bio = "Proveniente de la tundra del eterno invierno de rusia. Sova rastrea, encuentra y elimina a sus enemigos con gran eficiencia y precisión. Su arco personalizado y sus increíbles habilidades de exploración impedirán que sus enemigos puedan esconderse de él.",
                icon_q = "c_sova_q",
                nombre_q = "Proyectil eléctrico",
                desc_q = "Equipa un arco con un proyectil eléctrico y dispara para lanzarlo. El proyectil detonará al impactar y dañará a los jugadores cercanos. Mantén presionado el botón de disparo para aumentar el alcance del proyectil. Usa el disparo secundario para añadir hasta dos rebotes a su trayectoria.",
                icon_e = "c_sova_e",
                nombre_e = "Proyectil rastreador",
                desc_e = "Equipa un arco con un proyectil rastreador y dispara para lanzarlo. El proyectil se activará al impactar y revelará la ubicación de los enemigos cercanos que estén dentro de su alcance. Mantén presionado el botón de disparo para aumentar el alcance del proyectil. Usa el disparo secundario para añadir hasta dos rebotes a la trayectoria de la flecha.",
                icon_c = "c_sova_c",
                nombre_c = "Dron búho",
                desc_c = "Equipa un dron búho y dispara para desplegarlo y controlar su movimiento. Mientras lo controlas, dispara para lanzar un dardo marcador que revelará la ubicación de cualquier jugador al que impacte.",
                icon_x = "c_sova_x",
                nombre_x = "Furia del cazador",
                desc_x = "Equipa un arco con tres ráfagas de energía de largo alcance que atraviesan las paredes. Dispara para lanzar una ráfaga de energía frente a Sova que infligirá daño y revelará la ubicación de los enemigos impactados. Puedes volver a usar esta habilidad hasta dos veces más mientras su contador esté activo."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Viper",
                imagen = "c_viper",
                rol = "CONTROLADOR",
                rol_icon = "r_controller",
                bio = "Química estadounidense, Viper despliega varios artefactos químicos venenosos para controlar el campo de batalla y afectar la visión de los enemigos. Si las toxinas no asesinan a su presa, sin duda lo harán sus juegos mentales.",
                icon_q = "c_viper_q",
                nombre_q = "Nube venenosa",
                desc_q = "Equipa un emisor de gas y dispara para lanzarlo. Este permanecerá ahí durante toda la ronda. Vuelve a usar la habilidad para crear una nube de gas tóxica que usa combustible. Puedes volver a usar esta habilidad varias veces y puedes recuperarla para volver a desplegarla.",
                icon_e = "c_viper_e",
                nombre_e = "Cortina tóxica",
                desc_e = "Equipa un lanzador de gas y dispara para desplegar una larga hilera de emisores de gas. Vuelve a usar la habilidad para crear un muro de gas tóxico que usa combustible. Puedes volver a usar esta habilidad varias veces.",
                icon_c = "c_viper_c",
                nombre_c = "Mordedura",
                desc_c = "Equipa un lanzador de químicos. Dispara para lanzar un contenedor que se rompe al impactar contra el suelo. Este crea una zona química que permanece en el campo, la cual daña y ralentiza a los enemigos.",
                icon_x = "c_viper_x",
                nombre_x = "Fosa vipérea",
                desc_x = "Equipa un rociador y dispara para lanzar una nube de químicos en todas las direcciones alrededor de Viper, lo que crea una gran nube que reduce el alcance de visión y la vida máxima de los jugadores que estén dentro de ella."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Cypher",
                imagen = "c_cypher",
                rol = "CENTINELA",
                rol_icon = "r_sentinel",
                bio = "Cypher, el agente de información marroquí, es un sistema de vigilancia de un solo hombre que puede monitorear todos los movimientos de sus enemigos. No hay secreto que no descubra ni maniobra que no detecte. Cypher siempre está vigilando.",
                icon_q = "c_cypher_q",
                nombre_q = "Ciberjaula",
                desc_q = "Lanza al instante una ciberjaula frente a cypher. Actívala para crear una zona que bloquea la visión y ralentiza a los enemigos que la atraviesan.",
                icon_e = "c_cypher_e",
                nombre_e = "Cámara espía",
                desc_e = "Equipa una cámara espía. Dispara para colocarla en la ubicación seleccionada. Vuelve a usar esta habilidad para tomar el control de la vista de la cámara. Mientras la controlas, dispara para lanzar un dardo marcador que revelará la ubicación de cualquier jugador al que impacte.",
                icon_c = "c_cypher_c",
                nombre_c = "Cable trampa",
                desc_c = "Equipa un cable trampa. Dispara para colocar un cable trampa destructible y camuflado en la ubicación seleccionada que creará una línea entre ese lugar y la pared opuesta. Los jugadores enemigos que activen el cable trampa quedarán atados, revelados y aturdidos tras un momento si no destruyen el dispositivo a tiempo. Puedes recogerlo y volver a colocarlo.",
                icon_x = "c_cypher_x",
                nombre_x = "Asalto neural",
                desc_x = "Apunta hacia un jugador enemigo muerto y úsalo de inmediato para revelar la ubicación de todos los jugadores enemigos con vida."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Reyna",
                imagen = "c_reyna",
                rol = "DUELISTA",
                rol_icon = "r_duelist",
                bio = "Desde el corazón de méxico, Reyna llega para dominar los duelos uno contra uno y cada asesinato que realiza aumenta su poder. Su letalidad solo está limitada por tu destreza al usarla, por lo que su eficacia dependerá mucho de ello.",
                icon_q = "c_reyna_q",
                nombre_q = "Devorar",
                desc_q = "Los enemigos eliminados por Reyna dejan orbes de almas que duran 3 seg. Consume al instante un orbe de almas cercano y la cura rápidamente durante un breve momento. La vida superior a 100 que obtenga mediante esta habilidad se deteriorará con el tiempo. Si la emperatriz está activa, lanzará automáticamente esta habilidad sin consumir el orbe.",
                icon_e = "c_reyna_e",
                nombre_e = "Desechar",
                desc_e = "Consume al instante un orbe de almas cercano para volverse intangible durante un breve momento. También se vuelve invisible si la emperatriz está activa.",
                icon_c = "c_reyna_c",
                nombre_c = "La mirada",
                desc_c = "Equipa un ojo destructible y etéreo. Actívalo para lanzarlo a una corta distancia y todos los enemigos que lo vean se ofuscarán.",
                icon_x = "c_reyna_x",
                nombre_x = "La emperatriz",
                desc_x = "Entra al instante en un frenesí, lo que aumenta considerablemente la velocidad de disparo, equipamiento y recarga. Al lograr un asesinato, se restablece la duración."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Killjoy",
                imagen = "c_killjoy",
                rol = "CENTINELA",
                rol_icon = "r_sentinel",
                bio = "Killjoy, la prodigio de berlín, asegura fácilmente el campo de batalla con un arsenal de dispositivos. Si el daño que inflige su equipamiento no detiene a sus enemigos, la debilitación de sus robots la ayudarán a aniquilarlos.",
                icon_q = "c_killjoy_q",
                nombre_q = "Alarmabot",
                desc_q = "Equipa un alarmabot encubierto. Dispara para desplegar un bot que caza a los enemigos que entren en su alcance. Después de alcanzar a su objetivo, el bot explota, lo que inflige daño y aplica vulnerable. Mantén presionado equipar para recuperar un bot desplegado.",
                icon_e = "c_killjoy_e",
                nombre_e = "Torreta",
                desc_e = "Equipa una torreta. Dispara para desplegar una torreta que les dispara a los enemigos cercanos en un cono de 180 grados. Mantén presionado equipar para recuperar una torreta desplegada.",
                icon_c = "c_killjoy_c",
                nombre_c = "Nanoplaga",
                desc_c = "Equipa una granada de nanoplaga. Dispara para lanzar la granada. La nanoplaga queda encubierta al aterrizar. Activa nanoplaga para desplegar un mortal enjambre de nanobots.",
                icon_x = "c_killjoy_x",
                nombre_x = "Dispositivo inmovilizador",
                desc_x = "Equipa el dispositivo inmovilizador. Dispara para desplegar el dispositivo. Tras cargar, el dispositivo detiene a todos los enemigos dentro de su radio. Los enemigos pueden destruir el dispositivo."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Breach",
                imagen = "c_breach",
                rol = "INICIADOR",
                rol_icon = "r_initiator",
                bio = "Breach, el sueco biónico, lanza poderosas ráfagas cinéticas para abrirse paso a la fuerza a través del territorio enemigo. El daño y la interrupción que inflige garantizarán que ninguna pelea sea justa.",
                icon_q = "c_breach_q",
                nombre_q = "Fulgor",
                desc_q = "Equipa una carga cegadora y dispara para lanzar una ráfaga de acción rápida a través de una pared. La carga detonará y cegará a todos los jugadores que la vean.",
                icon_e = "c_breach_e",
                nombre_e = "Falla Sísmica",
                desc_e = "Equipa un rayo sísmico y mantén presionado el botón de disparo para aumentar la distancia. Suéltalo para iniciar el temblor y aturdir a todos los jugadores que estén en la zona.",
                icon_c = "c_breach_c",
                nombre_c = "Réplica",
                desc_c = "Equipa una carga de fusión y dispara para lanzar una ráfaga de acción lenta a través de una pared. La ráfaga infligirá daño masivo a todos los que estén en su alcance.",
                icon_x = "c_breach_x",
                nombre_x = "Trueno Rodante",
                desc_x = "Equipa una carga sísmica y dispara para enviar un temblor en cascada por una gran zona cónica. El temblor aturde y lanza por el aire a todos los que impacta."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Omen",
                imagen = "c_omen",
                rol = "CONTROLADOR",
                rol_icon = "r_controller",
                bio = "Un espectro de la memoria, Omen caza entre las sombras, ciega a los enemigos, se transporta a través del campo de batalla y deja que la paranoia los invada mientras intentan descubrir dónde atacará.",
                icon_q = "c_omen_q",
                nombre_q = "Paranoia",
                desc_q = "Lanza un proyectil de sombras que reduce el alcance de visión de todos los jugadores a los que alcanza durante un breve periodo. Este proyectil puede atravesar paredes.",
                icon_e = "c_omen_e",
                nombre_e = "Manto oscuro",
                desc_e = "Equipa un orbe de sombras y mira su indicador de alcance. Dispara para lanzarlo hacia la ubicación marcada. El orbe creará una esfera de sombras de larga duración que bloquea la visión. Mantén presionado el botón de disparo secundario mientras apuntas para alejar el marcador. Mantén presionada la tecla de la habilidad mientras apuntas para acercar el marcador.",
                icon_c = "c_omen_c",
                nombre_c = "Paso sombrío",
                desc_c = "Equipa la habilidad paso sombrío y mira su indicador de alcance. Dispara para comenzar una breve canalización y luego teletranspórtate hacia la ubicación marcada.",
                icon_x = "c_omen_x",
                nombre_x = "Desde las sombras",
                desc_x = "Equipa un mapa estratégico. Dispara para empezar a teletransportarte hacia la ubicación seleccionada. Mientras te teletransportas, Omen lucirá como una sombra que los enemigos podrán destruir para cancelar su teletransportación."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Jett",
                imagen = "c_jett",
                rol = "DUELISTA",
                rol_icon = "r_duelist",
                bio = "Proviene de corea del sur. El ágil y evasivo estilo de pelea de Jett le permite enfrentarse a riesgos que otros no pueden. Es imparable en todos los enfrentamientos y acaba con sus enemigos antes de que sepan qué los atacó.",
                icon_q = "c_jet_q",
                nombre_q = "Ráfaga ascendente",
                desc_q = "Propulsa a Jett hacia el aire al instante.",
                icon_e = "c_jet_e",
                nombre_e = "Impulso ciclón",
                desc_e = "Propulsa al instante a Jett hacia la dirección a la que se dirige. Si no se está moviendo, se propulsará hacia adelante.",
                icon_c = "c_jet_c",
                nombre_c = "Nube explosiva",
                desc_c = "Lanza un proyectil al instante que se convierte en una nube que bloquea la visión al impactar alguna superficie. Mantén presionada la tecla de la habilidad para cambiar la trayectoria del humo hacia donde apuntes.",
                icon_x = "c_jet_x",
                nombre_x = "Tormenta de cuchillas",
                desc_x = "Equipa un conjunto de cuchillos de gran precisión que se recargan al asesinar a un oponente. Dispara para lanzar un cuchillo hacia tu objetivo y usa el disparo secundario para lanzar todos los cuchillos restantes."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Raze",
                imagen = "c_raze",
                rol = "DUELISTA",
                rol_icon = "r_duelist",
                bio = "Raze llega de brasil con su explosiva personalidad y sus grandes armas. Gracias a su contundente estilo de juego, es muy buena para separar los enemigos atrincherados y para despejar espacios estrechos con una gran cantidad de explosiones.",
                icon_q = "c_raze_q",
                nombre_q = "Paquete explosivo",
                desc_q = "Lanza al instante un paquete explosivo que se adhiere a las superficies. Vuelve a usar la habilidad después de desplegar el paquete para detonarlo, lo que dañará y desplazará todo a su alcance. Raze no recibe daño por esta habilidad, pero sí sufrirá daño por caídas si salta muy alto.",
                icon_e = "c_raze_e",
                nombre_e = "Carcasas de pintura",
                desc_e = "Equipa una granada de racimo y dispara para lanzarla. La granada infligirá daño por su cuenta y creará submuniciones que también infligirán daño a cualquiera que se encuentre dentro de su alcance.",
                icon_c = "c_raze_c",
                nombre_c = "Bumbot",
                desc_c = "Equipa un bumbot y dispara para desplegarlo. El bot se moverá en línea recta por el suelo y rebotará en los muros. El bumbot marcará a cualquier enemigo que se encuentre en un cono frente a él y lo perseguirá. Si lo alcanza, explotará e infligirá daño masivo.",
                icon_x = "c_raze_x",
                nombre_x = "Tumbadivas",
                desc_x = "Equipa un lanzamisiles. Dispara para lanzar un misil que inflige daño de área masivo al entrar en contacto con algo."
            )
            infoDao.insertAgente(agente)
            agente = Agente(
                name = "Skye",
                imagen = "c_skye",
                rol = "INICIADOR",
                rol_icon = "r_initiator",
                bio = "Procedentes de australia, Skye y su manada de bestias se abren paso por territorio hostil. Con sus creaciones que obstaculizan al enemigo y su poder para sanar a los demás, los equipos serán más fuertes y seguros al lado de Skye.",
                icon_q = "c_skye_q",
                nombre_q = "Forjacaminos",
                desc_q = "Forjacaminos",
                icon_e = "c_skye_e",
                nombre_e = "Luz guía",
                desc_e = "Te equipas un artefacto de halcón. Dispara para enviarlo hacia adelante. Mantén presionado el disparo para guiar al halcón en la dirección de tu retícula. Reutilízala mientras el halcón está en vuelo para transformarlo en un destello que lanza un sonido de confirmación si un enemigo estaba dentro del alcance y campo visual.",
                icon_c = "c_skye_c",
                nombre_c = "Regeneración",
                desc_c = "Te equipas un artefacto de curación. Mantén presionado el disparo para canalizar, lo que cura aliados dentro del alcance y campo visual. Se puede reutilizar hasta que se agote la energía de curación. Skye no puede curarse a sí misma.",
                icon_x = "c_skye_x",
                nombre_x = "Buscadores",
                desc_x = "Te equipas un artefacto de buscador. Dispara para enviar tres buscadores a rastrear a los tres enemigos más cercanos. Si un buscador alcanza su objetivo, lo ofusca."
            )
            infoDao.insertAgente(agente)

            infoDao.deleteAllMapas()

            var mapa = Mapa(
                name = "Ascent",
                imagen = "m_ascent",
                bio = "Un campo abierto para pequeñas batallas de posicionamiento y desgaste dividen Ascenso en dos sitios. Puedes fortificar cada uno con puertas de bombas irreversibles; una vez puestas, tendrás que destruirlas o encontrar otra forma de pasar. Cede el menor territorio posible.",
                minimap = "m_ascent_minimap",
                map1 = "m_ascent_01",
                map2 = "m_ascent_02",
                map3 = "m_ascent_03",
                map4 = "m_ascent_04"
            )
            infoDao.insertMapa(mapa)
            mapa = Mapa(
                name = "Bind",
                imagen = "m_bind",
                bio = "Dos sitios. No existe un centro. Debes elegir derecha o izquierda. ¿Cuál será tu elección? Ambos ofrecen caminos directos para los atacantes y un par de teletransportadores unidireccionales facilitan el flanqueo.",
                minimap = "m_bind_minimap",
                map1 = "m_bind_01",
                map2 = "m_bind_02",
                map3 = "m_bind_03",
                map4 = "m_bind_04"
            )
            infoDao.insertMapa(mapa)
            mapa = Mapa(
                name = "Haven",
                imagen = "m_haven",
                bio = "Bajo un monasterio olvidado, emerge un clamor entre los agentes rivales que se enfrentan para controlar tres sitios. Hay más territorio por controlar, pero los defensores pueden usar el terreno adicional para realizar ataques agresivos.",
                minimap = "m_haven_minimap",
                map1 = "m_haven_01",
                map2 = "m_haven_02",
                map3 = "m_haven_03",
                map4 = "m_haven_04"
            )
            infoDao.insertMapa(mapa)
            mapa = Mapa(
                name = "Split",
                imagen = "m_split",
                bio = "Si quieres ir lejos, debes subir. Un par de sitios divididos por un centro elevado permite un movimiento rápido por medio de dos ascensores de cuerda. Cada sitio cuenta con una gran torre vital para el control. Recuerda mirar arriba antes de que todo vuele por los aires.",
                minimap = "m_split_minimap",
                map1 = "m_split_01",
                map2 = "m_split_02",
                map3 = "m_split_03",
                map4 = "m_split_04"
            )
            infoDao.insertMapa(mapa)
            mapa = Mapa(
                name = "Icebox",
                imagen = "m_icebox",
                bio = "Tu próximo campo de batalla es un sitio de excavación secreto de Kingdom en algún lugar del ártico. Los dos sitios para plantar están protegidos tanto por nieve como por metal, y no es fácil acceder a ellos de forma horizontal. Aprovecha las tirolesas y nunca te verán venir.",
                minimap = "m_icebox_minimap",
                map1 = "m_icebox_01",
                map2 = "m_icebox_02",
                map3 = "m_icebox_03",
                map4 = "m_icebox_04"
            )
            infoDao.insertMapa(mapa)

            infoDao.deleteAllArmas()
            var arma = Arma(
                name = "Classic",
                imagen = "w_classic_basic",
                bio = "El disparo principal realiza disparos precisos al estar quieto y tiene un modo de disparo alternativo para encuentros cercanos.",
                tipo = "ARMA DE MANO",
                creditos = "Gratis",
                capacidad = "12",
                damageH = "78",
                damageB = "26",
                damageL = "22"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Shorty",
                imagen = "w_shorty_basic",
                bio = "Una escopeta de cañón corto y ágil; letal a corta distancia, pero solo puede disparar dos veces antes de tener que recargarla. Se combina bien con las armas de largo alcance.",
                tipo = "ARMA DE MANO",
                creditos = "200",
                capacidad = "2",
                damageH = "36",
                damageB = "12",
                damageL = "10"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Frenzy",
                imagen = "w_frenzy_basic",
                bio = "Ametralladora ligera que destaca al disparar en movimiento. Su alta velocidad de disparo puede llegar a ser difícil de controlar, así que prueba ráfagas cortas a media distancia.",
                tipo = "ARMA DE MANO",
                creditos = "400",
                capacidad = "13",
                damageH = "78",
                damageB = "26",
                damageL = "22"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Ghost",
                imagen = "w_ghost_basic",
                bio = "La Ghost es precisa y tiene un gran cargador en caso de que falles. Los objetivos distantes requieren una tasa de fuego controlada. Presiona rápidamente el gatillo cuando veas lo blanco de sus ojos.",
                tipo = "ARMA DE MANO",
                creditos = "500",
                capacidad = "15",
                damageH = "105",
                damageB = "30",
                damageL = "26"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Sheriff",
                imagen = "w_sheriff_basic",
                bio = "Sus balas de alto impacto tienen mucho retroceso y se necesitan muchas agallas para dominarlas. Si dominas la Sheriff correctamente, tus enemigos sabrán que no tenían oportunidad.",
                tipo = "ARMA DE MANO",
                creditos = "800",
                capacidad = "6",
                damageH = "160",
                damageB = "55",
                damageL = "47"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Stinger",
                imagen = "w_stinger_basic",
                bio = "Este subfusil tiene mayor potencia a corta y larga distancia que los demás, pero a costa de velocidad de disparo y movilidad. Su cargador de 20 rondas se desperdicia en ráfagas llenas de retroceso, pero asesta golpes letales a media distancia con la mira y fuego controlado.",
                tipo = "SUBAMETRALLADORA",
                creditos = "1,000",
                capacidad = "20",
                damageH = "67",
                damageB = "27",
                damageL = "23"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Spectre",
                imagen = "w_spectre_basic",
                bio = "Un arma todoterreno con gran equilibrio de daño, velocidad de disparo y precisión, tanto a corta como larga distancia. Acecha los rincones de cada mapa y solo requiere que apuntes firmes para derribar a los enemigos a larga distancia.",
                tipo = "SUBAMETRALLADORA",
                creditos = "1,600",
                capacidad = "30",
                damageH = "78",
                damageB = "26",
                damageL = "22"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Bucky",
                imagen = "w_bucky_basic",
                bio = "Pesada, pero estable. El disparo principal de la Bucky es para mantener las esquinas cerradas o disparar a corta distancia. El disparo secundario sirve para los objetivos a medio alcance.",
                tipo = "ESCOPETA",
                creditos = "900",
                capacidad = "5",
                damageH = "44",
                damageB = "22",
                damageL = "19"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Judge",
                imagen = "w_judge_basic",
                bio = "La Judge es estable con disparos premeditados, pero volátil si la disparas rápidamente. El disparo principal ataca a los objetivos a corta distancia y tendrás que ser firme para alcanzar todo lo que esté más allá de la longitud de tu brazo.",
                tipo = "ESCOPETA",
                creditos = "1,600",
                capacidad = "7",
                damageH = "34",
                damageB = "17",
                damageL = "14"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Bulldog",
                imagen = "w_bulldog_basic",
                bio = "Es una bestia a la hora de intercambiar fuego. El disparo alternativo te permite utilizar una mira y lanzar ráfagas cortas y precisas a todo aquel que intente cazarte a media o larga distancia.",
                tipo = "RIFLE",
                creditos = "2,100",
                capacidad = "24",
                damageH = "116",
                damageB = "35",
                damageL = "30"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Guardian",
                imagen = "w_guardian_basic",
                bio = "Es el rifle para los tiradores entrenados. Más pesado y menos móvil en comparación con otros rifles, pero más preciso y potente. Comienza la cacería de tus enemigos a media y larga distancia.",
                tipo = "RIFLE",
                creditos = "2,400",
                capacidad = "",
                damageH = "195",
                damageB = "65",
                damageL = "49"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Phantom",
                imagen = "w_phantom_basic",
                bio = "Úsala en automático contra cualquiera que te ponga a prueba de cerca. Por otro lado, sus ráfagas cortas y controladas permiten derribar enemigos a cualquier distancia. Funciona mejor cuando no estás en movimiento.",
                tipo = "RIFLE",
                creditos = "2,900",
                capacidad = "30",
                damageH = "156",
                damageB = "39",
                damageL = "33"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Vandal",
                imagen = "w_vandal_basic",
                bio = "Sin embargo, su disparo extendido da como resultado menor estabilidad. La Vandal tiene un alto daño a distancia y recompensa a quienes se enfocan en un solo disparo a la cabeza.",
                tipo = "RIFLE",
                creditos = "2,900",
                capacidad = "25",
                damageH = "160",
                damageB = "40",
                damageL = "34"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Marshal",
                imagen = "w_marshal_basic",
                bio = "SNIPER",
                tipo = "Un ágil rifle de palanca con un solo zoom que puede mantener a raya a los enemigos. Una velocidad de disparo lenta significa que tienes que dar en el blanco o quedarás expuesto a los ataques.",
                creditos = "1,100",
                capacidad = "5",
                damageH = "202",
                damageB = "101",
                damageL = "85"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Operator",
                imagen = "w_operator_basic",
                bio = "Un rifle de francotirador con doble zoom de alta potencia. Extremadamente inmóvil, pero sus poderosas balas podrían devastar a un equipo con un solo disparo.",
                tipo = "SNIPER",
                creditos = "5,000",
                capacidad = "5",
                damageH = "255",
                damageB = "150",
                damageL = "120"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Ares",
                imagen = "w_ares_basic",
                bio = "El enorme cargador del Ares significa que sobresale en el fuego continuo e inflige daño masivo a grupos de enemigos.",
                tipo = "ARMA PESADA",
                creditos = "1,600",
                capacidad = "5O",
                damageH = "72",
                damageB = "30",
                damageL = "25"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Odin",
                imagen = "w_odin_basic",
                bio = "Disparos continuos de alto daño con una gran estabilidad. Pulveriza a los enemigos a corta distancia y usa el disparo alternativo para convertirte en una torreta viviente.",
                tipo = "ARMA PESADA",
                creditos = "3,200",
                capacidad = "100",
                damageH = "95",
                damageB = "38",
                damageL = "32"
            )
            infoDao.insertArma(arma)
            arma = Arma(
                name = "Cuchillo Tactico",
                imagen = "w_tactical_knife_basic",
                bio = "Cuando tengas dudas o se te acaben las balas, atácalos con esto. Te permite correr rápido, destruir objetos.",
                tipo = "MELEE",
                creditos = "0",
                capacidad = "-",
                damageH = "75",
                damageB = "50",
                damageL = "-"
            )
            infoDao.insertArma(arma)
        }
    }
}