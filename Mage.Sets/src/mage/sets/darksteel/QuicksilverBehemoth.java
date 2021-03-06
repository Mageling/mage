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
package mage.sets.darksteel;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.AttacksOrBlocksTriggeredAbility;
import mage.abilities.common.delayed.AtTheEndOfCombatDelayedTriggeredAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.ReturnToHandSourceEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;

/**
 *
 * @author fireshoes
 */
public class QuicksilverBehemoth extends CardImpl {

    public QuicksilverBehemoth(UUID ownerId) {
        super(ownerId, 30, "Quicksilver Behemoth", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{6}{U}");
        this.expansionSetCode = "DST";
        this.subtype.add("Beast");
        this.power = new MageInt(4);
        this.toughness = new MageInt(5);

        // Affinity for artifacts
        this.addAbility(new AffinityForArtifactsAbility());

        // When Quicksilver Behemoth attacks or blocks, return it to its owner's hand at end of combat.
        Effect effect = new CreateDelayedTriggeredAbilityEffect(
                new AtTheEndOfCombatDelayedTriggeredAbility(new ReturnToHandSourceEffect(true)));
        effect.setText("return it to its owner's hand at end of combat");
        this.addAbility(new AttacksOrBlocksTriggeredAbility(effect, false));
    }

    public QuicksilverBehemoth(final QuicksilverBehemoth card) {
        super(card);
    }

    @Override
    public QuicksilverBehemoth copy() {
        return new QuicksilverBehemoth(this);
    }
}
