/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.newphyrexia;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.game.Game;
import mage.game.permanent.token.BeastToken;
import mage.watchers.common.CreaturesDiedWatcher;

/**
 *
 * @author North
 */
public class FreshMeat extends CardImpl {

    public FreshMeat(UUID ownerId) {
        super(ownerId, 109, "Fresh Meat", Rarity.RARE, new CardType[]{CardType.INSTANT}, "{3}{G}");
        this.expansionSetCode = "NPH";

        // Put a 3/3 green Beast creature token onto the battlefield for each creature put into your graveyard from the battlefield this turn.
        this.getSpellAbility().addWatcher(new CreaturesDiedWatcher());
        this.getSpellAbility().addEffect(new CreateTokenEffect(new BeastToken(), new FreshMeatDynamicValue()));
    }

    public FreshMeat(final FreshMeat card) {
        super(card);
    }

    @Override
    public FreshMeat copy() {
        return new FreshMeat(this);
    }
}

class FreshMeatDynamicValue implements DynamicValue {

    @Override
    public int calculate(Game game, Ability sourceAbility, Effect effect) {
        CreaturesDiedWatcher watcher = (CreaturesDiedWatcher) game.getState().getWatchers().get("CreaturesDiedWatcher");
        if (watcher != null) {
            return watcher.getAmountOfCreaturesDiesThisTurn(sourceAbility.getControllerId());
        }
        return 0;
    }

    @Override
    public FreshMeatDynamicValue copy() {
        return new FreshMeatDynamicValue();
    }

    @Override
    public String toString() {
        return "1";
    }

    @Override
    public String getMessage() {
        return "creature put into your graveyard from the battlefield this turn";
    }
}
