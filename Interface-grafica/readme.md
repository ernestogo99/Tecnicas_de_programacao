# Swing

O Swing é um framework que disponibiliza um conjunto de elementos gráficos para ser utilizado na plataforma Java. 


# Principais Componentes

- JFrame: representa a janela do programa com barra de título, ícone, botões de comando, etc. Entre os principais métodos temos o pack() que compacta a janela para o tamanho dos componentes, setSize(int, int) que define a largura e altura da janela, setLocation(int, int) que define a posição da janela na tela (x,y), setBounds(int, int, int, int) que define posição e tamanho, setVisible(boolean) que exibe a janela e setDefaultCloseOperation(int) que define o que ocorre quando o usuário tenta fechar a janela (as opções são: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, DISPOSE_ON_CLOSE, EXIT_ON_CLOSE).
- JPanel: representa um tipo básico de container para inserção de componentes. Entre os principais métodos temos add(Component, int) que adiciona o componente definindo sua posição e setLayout(LayoutManagaer) que altera o tipo de layout.
- JLabel: representa um rótulo de texto. Entre os principais métodos temos o setText(String) que altera o texto e getText() que retorna o texto atual.
- JTextField: representa um campo de texto onde o usuário pode informar um texto em uma linha. Entre os principais métodos temos setText(String) que altera o texto e getText() que retorna o texto atual.
- JPasswordField: representa um campo de texto protegido, subclasse de JTextField. O principal método é o setEchoChar(char) que define o caractere que aparece ao digitar um texto.
- JTextArea: representa uma caixa onde o usuário pode informar várias linhas de texto. Entre os principais métodos temos o setText(String) que altera o texto, getText() que retorna o texto atual, getSelectedText() que retorna o texto selecionado pelo usuário e insert(String, int) que insere um texto na posição especificada.
- JCheckBox: representa uma caixa de seleção e permite selecionar ou não uma opção. Entre os principais métodos temos o setSelected(boolean) que altera o estado da caixa de seleção e o método isSelected() que retorna true se a caixa estiver marcada e false se não estiver marcada.
- JRadioButton: representa um componente que permite seleciona uma entre diversas opções. O JRadioButton é semelhante ao JCheckBox, inclusive com os mesmos construtores e métodos.
- JComboBox: representa uma caixa de combinação, da qual o usuário pode selecionar uma opção. Entre os principais métodos temos o addItem(Object) que adiciona um item à lista de opções, setEditable(boolean) que permite ao usuário digitar uma opção, getSelectedIndex() que retorna a posição do item atualmente selecionado, getSelectedItem() que retorna o texto do item atualmente selecionado, setSelectedIndex(int) que seleciona o item da posição especificada e setSelectedIndex(Object) que seleciona o objeto especificado na lista.
- JList: representa uma lista de opções que permite a seleção de mais de um item simultaneamente. Entre os principais métodos temos o setListData(Object[]) que preenche ou altera os itens de uma lista, getSelectedValues() que retorna um array de objetos contendo itens selecionados na lista.
- JButton: representa um botão destinado a executar uma ação. Entre os principais métodos temos o setText(String) que altera o texto do botão e setIcon(Icon) que altera o ícone do botão.


# Layouts

## Borderlayout

O BorderLayout é um gerenciador de layout (layout manager) do Java Swing que organiza os componentes em cinco regiões principais:

- NORTH (Norte): parte superior.

- SOUTH (Sul): parte inferior.

- EAST (Leste): lado direito.

- WEST (Oeste): lado esquerdo.

- CENTER (Centro): ocupa o espaço restante.

Só pode haver no máximo um componente por região,
O componente adicionado ao CENTER vai ocupar todo o espaço restante após posicionar os outros.

# FlowLayout

- Nele os componentes são adicionados da esquerda para a direita, e quando o espaço acaba, ele quebra a linha.

- Os componentes fluem horizontalmente (por padrão).

- Quando não cabe mais, ele passa para a próxima linha.

- Alinhamento padrão: centralizado (FlowLayout.CENTER).

- Pode ser ajustado para LEFT, RIGHT, CENTER, LEADING e TRAILING.

Permite definir espaçamento horizontal e vertical entre os componentes.


# GridLayout

É um layout que organiza a tela por linhas e colunas


-Divide o espaço em células iguais (mesmo tamanho).

-Cada célula pode conter um único componente.

-Você define o número de linhas e colunas ao criar.

-Os componentes são adicionados na ordem de leitura: esquerda para direita, de cima para baixo.

-Não há sobreposição nem redimensionamento individual: todos os componentes têm tamanho igual.



# ActionListener

É uma interface que permite para ouvir e receber eventos do java swing
um evento indica que uma ação ocorreu.

