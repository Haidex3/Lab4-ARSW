package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface BlueprintFilter {
    /**
     * Aplica un proceso de filtrado sobre el plano dado.
     * @param blueprint Plano original.
     * @return Nuevo plano filtrado.
     */
    Blueprint applyFilter(Blueprint blueprint);
}
