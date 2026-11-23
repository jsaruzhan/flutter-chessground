#!/usr/bin/env bash                                                                                                                                                                
# gen-android-res.sh                                                                                                                                                               
# Generates Android drawables for the chessground Android library from assets/.                                                                                                    
# Re-run after adding/removing/updating boards or piece sets, then commit android/src/main/res/.           

set -euo pipefail                                                                                                                                                                  
                                                                                                                                                                                     
REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"                                                                                                                                      
BOARDS_SRC="$REPO_ROOT/assets/boards"                                                                                                                                              
PIECES_SRC="$REPO_ROOT/assets/piece_sets"                                                                                                                                          
RES="$REPO_ROOT/android/src/main/res"   

# camelCase / kebab-case -> snake_case (Android resource names: [a-z0-9_] only)                                                                                                    
to_snake() { echo "$1" | sed -E 's/([a-z0-9])([A-Z])/\1_\2/g; s/-/_/g' | tr 'A-Z' 'a-z'; }      

                               
find "$RES" \( -name 'board_*' -o -name 'piece_*' \) -delete 2>/dev/null || true                                                                                                   
for d in nodpi mdpi xhdpi xxhdpi xxxhdpi; do mkdir -p "$RES/drawable-$d"; done              

BOARD_ENTRIES=(                                                                                                                                                                    
    "blue2        blue2.jpg"                                                                                                                                                       
    "blue3        blue3.jpg"                                                                                                                                                       
    "blueMarble   blue-marble.jpg"                                                                                                                                                 
    "canvas       canvas2.jpg"                                                                                                                                                     
    "greenPlastic green-plastic.webp"                                                                                                                                              
    "grey         grey.jpg"                                                                                                                                                        
    "horsey       horsey.jpg"                                                                                                                                                      
    "leather      leather.jpg"                                                                                                                                                     
    "maple        maple.jpg"                                                                                                                                                       
    "maple2       maple2.jpg"                                                                                                                                                      
    "marble       marble.jpg"                                                                                                                                                      
    "metal        metal.jpg"                                                                                                                                                       
    "newspaper    newspaper.webp"                                                                                                                                                  
    "olive        olive.jpg"                                                                                                                                                       
    "pinkPyramid  pink-pyramid.webp"                                                                                                                                               
    "purple       purple.webp"                                                                                                                                                     
    "purpleDiag   purple-diag.webp"                                                                                                                                                
    "wood         wood.jpg"                                                                                                                                                        
    "wood2        wood2.jpg"                                                                                                                                                       
    "wood3        wood3.jpg"                                                                                                                                                       
    "wood4        wood4.jpg"                                                                                                                                                       
)       

  
board_count=0

for entry in "${BOARD_ENTRIES[@]}"; do
    read -r name src <<<"$entry"
    cp "$BOARDS_SRC/$src" "$RES/drawable-nodpi/board_$(to_snake "$name").${src##*.}"
    board_count=$((board_count + 1))
done
echo "  boards: $board_count written"

PIECES=(bB bK bN bP bQ bR wB wK wN wP wQ wR)                                                                                                                                       
  set_count=0                                                                                                                                                                        
  for set_dir in "$PIECES_SRC"/*/; do                                                                                                                                                
      set_name=$(basename "$set_dir")                                                                                                                                                
      if [[ ! -f "$set_dir/bB.webp" ]]; then echo "  skipping '$set_name' (non-standard layout)"; continue; fi                                                                       
      set_res=$(to_snake "$set_name")                                                                                                                                                
      for piece in "${PIECES[@]}"; do                                                                                                                                                
          src="$piece"                                                                                                                                                               
          [[ "$set_name" == "disguised" ]] && src="${piece:0:1}"   # w.webp / b.webp                                                                                                 
          out="piece_${set_res}_$(echo "$piece" | tr 'A-Z' 'a-z').webp"                                                                                                              
          cp "$set_dir/$src.webp"      "$RES/drawable-mdpi/$out"                                                                                                                     
          cp "$set_dir/2.0x/$src.webp" "$RES/drawable-xhdpi/$out"                                                                                                                    
          cp "$set_dir/3.0x/$src.webp" "$RES/drawable-xxhdpi/$out"                                                                                                                   
          [[ -f "$set_dir/4.0x/$src.webp" ]] && cp "$set_dir/4.0x/$src.webp" "$RES/drawable-xxxhdpi/$out"                                                                            
      done                                                                                                                                                                           
      set_count=$((set_count + 1))                                                                                                                                                   
  done
  echo "  pieces: $set_count sets x ${#PIECES[@]} = $((set_count * ${#PIECES[@]})) drawables"
  echo "Done. Commit the changes in android/src/main/res/"              