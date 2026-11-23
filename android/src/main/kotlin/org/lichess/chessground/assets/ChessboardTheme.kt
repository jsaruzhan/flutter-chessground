package org.lichess.chessground.assets                                                                                                                                             
                                                                                                                                                                                     
  import android.content.Context                                                                                                                                                     
  import androidx.annotation.DrawableRes            


data class ChessboardTheme(                                                                                                                                                        
      /** ARGB colour of light squares. */                                                                                                                                           
      val lightSquare: Int,                                                                                                                                                          
      /** ARGB colour of dark squares. */                                                                                                                                            
      val darkSquare: Int,                                                                                                                                                           
      /** ARGB colour (with alpha) of the last-move highlight. */                                                                                                                    
      val lastMoveHighlight: Int,                                                                                                                                                    
      /** Drawable resource name for image-backed themes; `null` means solid colours. */                                                                                             
      val boardDrawableName: String?,                                                                                                                                                
      /** Piece set name as used by Dart (`PieceSet.name`, camelCase). */                                                                                                            
      val pieceSet: String,                                                                                                                                                          
  ) {   
    /** Resource ID of the board image, or `0` for solid-colour themes. */                                                                                                         
      @DrawableRes                                                                                                                                                                   
      fun boardDrawableRes(context: Context): Int =                                                                                                                                  
          boardDrawableName?.let { drawableId(context, it) } ?: 0                                                                                                                    
                                                                                                                                                                                     
      /**                                                                                                                                                                            
       * Resource ID of a piece image, e.g. `pieceDrawableRes(ctx, "wK")`.                                                                                                           
       * Returns `0` if the set/piece doesn't exist.                                                                                                                                 
       */                                                                                                                                                                            
      @DrawableRes                                                                                                                                                                   
      fun pieceDrawableRes(context: Context, piece: String): Int =                                                                                                                   
          drawableId(context, pieceDrawableName(pieceSet, piece))                                                                                                                    
                                                                                                                                                                                     
      companion object {                                                                                                                                                             
          const val DEFAULT_PIECE_SET = "staunty"                                                                                                                                    
          const val DEFAULT_THEME_NAME = "brown"                                                                                                                                     
                                                                                                                                                                                     
          private const val DEFAULT_LAST_MOVE = 0x809CC700.toInt() // rgb(156,199,0) @ 50.2%                                                                                         
          private const val TEAL_LAST_MOVE = 0x69009BC7           // rgb(0,155,199)  @ 41%        

          fun from(themeName: String, pieceSet: String = DEFAULT_PIECE_SET): ChessboardTheme {                                                                                       
              fun t(light: Int, dark: Int, board: String? = null, lastMove: Int = DEFAULT_LAST_MOVE) =                                                                               
                  ChessboardTheme(                                                                                                                                                   
                      lightSquare = 0xFF000000.toInt() or light,                                                                                                                     
                      darkSquare = 0xFF000000.toInt() or dark,                                                                                                                       
                      lastMoveHighlight = lastMove,                                                                                                                                  
                      boardDrawableName = board,                                                                                                                                     
                      pieceSet = pieceSet,                                                                                                                                           
                  )                 
                   return when (themeName) {                                                                                                                                              
                  // Solid-colour themes                                                                                                                                             
                  "system" -> t(0xF0D9B6, 0xB58863)                                                                                                                                  
                  "blue" -> t(0xDEE3E6, 0x8CA2AD)                                                                                                                                    
                  "green" -> t(0xFFFFDD, 0x86A666, lastMove = TEAL_LAST_MOVE)                                                                                                        
                  "ic" -> t(0xECECEC, 0xC1C18E)                                                                                                                                      
                                                                                                                                                                                     
                  // Image-backed themes                                                                                                                                             
                  "blue2" -> t(0x97B2C7, 0x546F82, "board_blue2")                                                                                                                    
                  "blue3" -> t(0xD9E0E6, 0x315991, "board_blue3")                                                                                                                    
                  "blueMarble" -> t(0xEAE6DD, 0x7C7F87, "board_blue_marble")                                                                                                         
                  "canvas" -> t(0xD7DAEB, 0x547388, "board_canvas")                                                                                                                  
                  "greenPlastic" -> t(0xF2F9BB, 0x59935D, "board_green_plastic", TEAL_LAST_MOVE)                                                                                     
                  "grey" -> t(0xB8B8B8, 0x7D7D7D, "board_grey")                                                                                                                      
                  "horsey" -> t(0xF0D9B5, 0x946F51, "board_horsey")                                                                                                                  
                  "leather" -> t(0xD1D1C9, 0xC28E16, "board_leather")                                                                                                                
                  "maple" -> t(0xE8CEAB, 0xBC7944, "board_maple")                                                                                                                    
                  "maple2" -> t(0xE2C89F, 0x996633, "board_maple2")                                                                                                                  
                  "marble" -> t(0x93AB91, 0x4F644E, "board_marble", TEAL_LAST_MOVE)                                                                                                  
                  "metal" -> t(0xC9C9C9, 0x727272, "board_metal")                                                                                                                    
                  "newspaper" -> t(0xFFFFFF, 0x8D8D8D, "board_newspaper")                                                                                                            
                  "olive" -> t(0xB8B19F, 0x6D6655, "board_olive")                                                                                                                    
                  "pinkPyramid" -> t(0xE8E9B7, 0xED7272, "board_pink_pyramid")                                                                                                       
                  "purple" -> t(0x9F90B0, 0x7D4A8D, "board_purple")                                                                                                                  
                  "purpleDiag" -> t(0xE5DAF0, 0x957AB0, "board_purple_diag")                                                                                                         
                  "wood" -> t(0xD8A45B, 0x9B4D0F, "board_wood")                                                                                                                      
                  "wood2" -> t(0xA38B5D, 0x6C5017, "board_wood2")                                                                                                                    
                  "wood3" -> t(0xD0CECA, 0x755839, "board_wood3")                                                                                                                    
                  "wood4" -> t(0xCAAF7D, 0x7B5330, "board_wood4")                                                                                                                    
                                                                                                                                                                                     
                  else -> t(0xF0D9B6, 0xB58863) // "brown" and any unknown value                                                                                                     
              }                     
          }
          fun pieceDrawableName(pieceSet: String, piece: String): String =                                                                                                           
              "piece_${toSnake(pieceSet)}_${piece.lowercase()}"                                                                                                                      
                                                                                                                                                                                     
          private fun toSnake(name: String): String =                                                                                                                                
              name.replace(Regex("([a-z0-9])([A-Z])"), "$1_$2").replace('-', '_').lowercase()                                                                                        
                                                                                                                                                                                     
          @DrawableRes                                                                                                                                                               
          private fun drawableId(context: Context, name: String): Int =                                                                                                              
              // Lookup by name is needed because theme/piece set are only known at runtime.                                                                                         
              context.resources.getIdentifier(name, "drawable", context.packageName)                                                                                                 
      }                                                                                                                                                                              
  }                                                                                                                                                                                  
                  