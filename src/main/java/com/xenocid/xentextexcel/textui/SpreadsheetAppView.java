package com.xenocid.xentextexcel.textui;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

/**
 * Created by xenocid on 3/10/15.
 */
public class SpreadsheetAppView {
    private Terminal    terminal;
    private Screen      screen;
    private GUIScreen   gui;

    public SpreadsheetAppView() {
        super();
        this.terminal = TerminalFacade.createTerminal();
    }

    public void run() {
        terminal.enterPrivateMode();

        while(true) {
            terminal.applyForegroundColor(Terminal.Color.GREEN);
            terminal.applyBackgroundColor(Terminal.Color.BLACK);

            TerminalSize screenSize = terminal.getTerminalSize();
            gui = TerminalFacade.createGUIScreen(terminal);

            gui.getScreen().startScreen();


            Key key = screen.readInput();
            if(key.getKind() == Key.Kind.Enter) {
                break;
            }
        }

        gui.getScreen().stopScreen();
        terminal.exitPrivateMode();
    }
}
