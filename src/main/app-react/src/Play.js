import React from 'react';
import GridCell from './GridCell';

class Cell {
}

export class Play extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: "", grid: null, game: null, rows: null, columns: null
    };
  }

  drawCell(cell) {
    console.log('@drawCell()');
    return (<GridCell uncover={() => this.uncover(cell.cellPosition)} x={cell.cellPosition.x} y={cell.cellPosition.y} game={this.props.location.state.gameId} status={cell.cellStatus} adjacentMinedCells={cell.adjacentMinedCells} />);
  }

  drawRow(row) {
    return (
      <div style={{ whiteSpace: 'pre-line' }} >
        {row?.map((cell) => (
          this.drawCell(cell)
        ))}
      </div>
    )
  }

  componentDidMount() {
    this.loadGame();
  }

  async loadGame() {
    console.log('@loadGame()');
    const response = await fetch('http://localhost:18888/games/load/' + this.props.location.state.gameId);
    const json = await response.json();
    const gridarr = [];
    var rows = json.grid.rowcount;
    var columns = json.grid.columncount;
    var cells = json.grid.cells;

    console.log(JSON.stringify(cells));

    console.log('total cells ' + cells.length);

    for (let i = 0; i < rows; i++) {
      gridarr.push(cells[i]);
    }
    this.setState({ game: json, grid: gridarr, rows: rows, columns: columns });
  }

  render() {
    return (
      <div>
        {this.state.grid?.map((row) => (
          this.drawRow(row)
        ))}
      </div>
    )
  }

  async uncover(cellPosition) {
    const response = await fetch('http://localhost:18888/games/' + this.props.location.state.gameId + '/uncover/' + cellPosition.x + '/' + cellPosition.y, {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: '' // body data type must match "Content-Type" heade
    });
    this.loadGame();
  }
}
