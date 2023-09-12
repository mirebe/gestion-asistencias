/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import de.jensd.fx.glyphs.GlyphIconName;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;

/**
 *
 * @author mireb
 */
public enum IconosAwesome {
    INFO(FontAwesomeIconName.INFO_CIRCLE),
    WARN(FontAwesomeIconName.WARNING),
    ERROR(FontAwesomeIconName.TIMES_CIRCLE),
    PREGUNTA(FontAwesomeIconName.QUESTION_CIRCLE),
    CORRECTO(FontAwesomeIconName.CHECK_CIRCLE);

    private final GlyphIconName icono;

    private IconosAwesome(GlyphIconName icono) {
        this.icono = icono;
    }

    public GlyphIconName getIcono() {
        return icono;
    }
}
