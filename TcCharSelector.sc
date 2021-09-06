SCRIPT_START
{
NOP

WAIT 0
LVAR_INT scplayer
LVAR_INT pmodel pmodel1 pmodel2 pmodel3 pmodel4 pmodel5 pmodel6 pmodel7 pmodel8 pmodel9 pmodel10
LVAR_INT uselast lastused charmenu selected skinpick skinblip pickmodel check
LVAR_FLOAT x y z xa xb ya yb za zb

GET_PLAYER_CHAR 0 scplayer

//Reading Ini
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "DefaultSkin" "Model" pmodel
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "DefaultSkin" "UseLast" uselast
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "DefaultSkin" "LastUsed" lastused
    // READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Tweaks" "PickupIcon" pickicon
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pickup" "PickModel" pickmodel
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pickup" "ShowCheckpoint" check
    READ_FLOAT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pickup" "X" x
    READ_FLOAT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pickup" "Y" y
    READ_FLOAT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pickup" "Z" z
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin1" "Model" pmodel1
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin2" "Model" pmodel2
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin3" "Model" pmodel3
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin4" "Model" pmodel4
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin5" "Model" pmodel5
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin6" "Model" pmodel6
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin7" "Model" pmodel7
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin8" "Model" pmodel8
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin9" "Model" pmodel9
    READ_INT_FROM_INI_FILE "cleo\TcCharSelector.ini" "Pskin" "Model" pmodel10
//

GOSUB pickup
GOSUB change_skin

xa = x + 2.0
xb = x - 2.0
ya = y + 2.0
yb = y - 2.0
za = z + 2.0
zb = z - 2.0

main_loop:
WAIT 0

    IF IS_CHAR_IN_AREA_3D scplayer xa ya za xb yb zb check
        PRINT_STRING_NOW "Pressione Y para trocar de personagem" 2000
        IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
            GOSUB select_char
        ENDIF
    ENDIF

GOTO main_loop

pickup:
    WAIT 0
    REMOVE_CLEO_BLIP skinblip
    REQUEST_MODEL pickmodel
    LOAD_ALL_MODELS_NOW
    ADD_CLEO_BLIP RADAR_SPRITE_TRIADS_CASINO x y TRUE 255 255 255 255 skinblip
    CREATE_PICKUP pickmodel 6 x y z skinpick
RETURN

change_skin:
    WAIT 0

    IF uselast = 1
        pmodel = lastused
        GOSUB load_pmodel
    ENDIF

    SET_PLAYER_MODEL 0 pmodel
    WRITE_INT_TO_INI_FILE pmodel "cleo\TcCharSelector.ini" "DefaultSkin" "LastUsed"
RETURN

load_pmodel:
    IF NOT HAS_MODEL_LOADED pmodel
        REQUEST_MODEL pmodel
        LOAD_ALL_MODELS_NOW
    ENDIF
RETURN

select_char:
    WAIT 500
    CREATE_MENU MENUNM (30.0 170.0) (280.0) 1 TRUE TRUE 0 (charmenu)
    SET_MENU_COLUMN charmenu 0 DUMMY (CHAR1 CHAR2 CHAR3 CHAR4 CHAR5 CHAR6 CHAR7 CHAR8 CHAR9 CHAR10 DUMMY DUMMY)
    SET_PLAYER_CONTROL 0 0

    WHILE TRUE
        WAIT 0
        IF IS_BUTTON_PRESSED PAD1 CROSS
            GET_MENU_ITEM_SELECTED charmenu (selected)
            SWITCH selected
                CASE 0
                    REQUEST_MODEL pmodel1
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel1 
                    BREAK
                CASE 1
                    REQUEST_MODEL pmodel2
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel2 
                    BREAK
                CASE 2
                    REQUEST_MODEL pmodel3
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel3 
                    BREAK
                CASE 3
                    REQUEST_MODEL pmodel4
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel4 
                    BREAK
                CASE 4
                    REQUEST_MODEL pmodel5
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel5
                    BREAK
                CASE 5
                    REQUEST_MODEL pmodel6
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel6 
                    BREAK
                CASE 6
                    REQUEST_MODEL pmodel7
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel7
                    BREAK
                CASE 7
                    REQUEST_MODEL pmodel8
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel8 
                    BREAK
                CASE 8
                    REQUEST_MODEL pmodel9
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel9 
                    BREAK
                CASE 9
                    REQUEST_MODEL pmodel10
                    WAIT 1000
                    SET_PLAYER_MODEL 0 pmodel10 
                    BREAK
            ENDSWITCH
            BREAK
        ENDIF

        IF IS_BUTTON_PRESSED PAD1 TRIANGLE // action key
            BREAK
        ENDIF
    ENDWHILE

    DELETE_MENU charmenu
    WRITE_INT_TO_INI_FILE pmodel1 "cleo\TcCharSelector.ini" "DefaultSkin" "LastUsed"
    SET_PLAYER_CONTROL 0 1
RETURN

}
SCRIPT_END