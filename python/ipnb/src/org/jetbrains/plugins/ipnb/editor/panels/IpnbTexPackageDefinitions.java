package org.jetbrains.plugins.ipnb.editor.panels;

import org.w3c.dom.Element;
import uk.ac.ed.ph.snuggletex.SnugglePackage;
import uk.ac.ed.ph.snuggletex.dombuilding.CommandHandler;
import uk.ac.ed.ph.snuggletex.internal.DOMBuilder;
import uk.ac.ed.ph.snuggletex.internal.SnuggleParseException;
import uk.ac.ed.ph.snuggletex.tokens.CommandToken;
import uk.ac.ed.ph.snuggletex.tokens.FlowToken;

import java.util.List;

import static uk.ac.ed.ph.snuggletex.definitions.Globals.MATH_MODE_ONLY;

public final class IpnbTexPackageDefinitions {

    private static final SnugglePackage ourPackage;
    
    public static SnugglePackage getPackage() {
        return ourPackage;
    }
    
    static {
      ourPackage = new SnugglePackage("Ipnb");

      ourPackage.addComplexCommandSameArgMode("dot", false, 1, MATH_MODE_ONLY, new CommandHandler() {
        @Override
        public void handleCommand(DOMBuilder builder, Element parentElement, CommandToken token) throws SnuggleParseException {
          List<FlowToken> content = token.getArguments()[0].getContents();
          Element result = builder.appendMathMLElement(parentElement, "mover");
          builder.handleMathTokensAsSingleElement(result, content);
          builder.appendMathMLOperatorElement(result, "\u02D9");
        }
      }, null);
      ourPackage.addComplexCommandSameArgMode("pi", false, 0, MATH_MODE_ONLY, new CommandHandler() {
        @Override
        public void handleCommand(DOMBuilder builder, Element parentElement, CommandToken token) throws SnuggleParseException {
          builder.appendMathMLOperatorElement(parentElement, "\u03c0");
        }
      }, null);
    }
}
