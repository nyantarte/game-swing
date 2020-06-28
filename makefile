RUN_CMD=java -cp ./out
BUILD_CMD=javac -d ./out
SRC=LispLexerParser.java\
    LispParsingError.java\
	RootboxCharactor.java\
	GridLayoutPanel.java\
	DevelopPanel.java\
	RootboxCharactorListPanel.java\
	LispActionListener.java\
	CreateRootboxCharactorFunc.java\
	CreateFieldMapFunc.java\
	CreatePanelFunc.java\
	PairFunc.java\
	MapFunc.java\
	FieldMapListPanel.java\
	MapResultPanel.java\
	FieldMapEditPanel.java\
	RootboxCharactorEditPanel.java\
	CollectionButtonClickListener.java\
	CharactorsButtonClickListener.java\
	ReturnButtonClickListener.java\
	ListItemSelectListener.java\
	PartyButtonClickListener.java\
	PartyMemberClickListener.java\
	BattleCharactor.java\
	IMoveFunc.java\
	AStarMoveFunc.java\
	Party.java\
	MapParty.java\
	FieldMap.java\
	FloorMap.java\
	Tile.java\
	TileSet.java\
	MapPanel.java\
	RootboxPanel.java\
	MainWindow.java\
	Vector.java\
	Pair.java\
	Triple.java\
	Main.java
ALL:
	$(BUILD_CMD) $(SRC)

RUN:
	$(RUN_CMD) Main test.lsp