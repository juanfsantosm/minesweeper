import React from 'react';
import GridCell from './GridCell';

class CellPosition {

}
export class TodoApp extends React.Component {

  constructor(props) {
    super(props);
    this.state = { items: [], text: '', rows: 5, columns: 5 , data: []};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  drawCell(x, y) {
    return (<GridCell x={x} y={y} />);
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:18888/grid/1623718786055');
    const json = await response.json();
    this.setState({ data: json });
  }

  render() {
    console.log('state.data ' + this.state.data);    
    let unCovered = this.state.data.uncoveredPositions;
    let rowCount = this.state.data.rowcount;
    let columnCount = this.state.data.columncount;

    const grid = [];

    for (let row = 0; row < rowCount; row++) {
      const currentRow = [];
      for (let col = 0; col < columnCount; col++) {
        currentRow.push(row);
      }
      grid.push(currentRow);
    }

    return (
      grid.map((rowx, rowId) => {
        return (
          <p style={{ whiteSpace: 'pre' }}>
            {rowx.map((col, colId) => {
              return (
                this.drawCell(rowId, colId)
              );
            })
            }
          </p>
        )
      })
    );
  }

  handleChange(e) {
    this.setState({ text: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    if (this.state.text.length === 0) {
      return;
    }
    const newItem = {
      text: this.state.text,
      id: Date.now()
    };
    this.setState(state => ({
      items: state.items.concat(newItem),
      text: ''
    }));
  }
}
