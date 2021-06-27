import React from 'react';
import GridCell from './GridCell';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';

export class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [], text: '', rows: 5, columns: 5, data: [],
      games: [], formDialogVisible: false, newGameRequestRows: 4, newGameRequestCols: 4, newGameRequestMined: 3
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  drawCell(x, y) {
    return (<GridCell x={x} y={y} />);
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:18888/games/listsaved');
    const json = await response.json();
    this.setState({ data: json });
  }

  render() {
    const headerCard = <div style={{ textAlign: "center" }}><h1>{"Some title"}</h1></div>;
    const headerDatatable =
      <div className="p-grid">
        <Button tooltip={"New game"}
          tooltipOptions={{ position: 'top' }} type="button"
          style={{ marginRight: '.5em' }}
          onClick={(e) => { this.setState({ formDialogVisible: true }); }} >New game</Button>
      </div>;
    return (
      <div>
        <div className="p-grid">
          <div className="p-col-12">
            <Card header={headerCard} style={{ padding: "1em" }}>
              <div className="p-grid">
                <div className="p-col-12">
                  <DataTable value={this.state.games} paginator={false} rows={10}
                    responsive={true} emptyMessage={"No results"} lazy={false}
                    header={headerDatatable}>
                    <Column selectionMode="single" style={{ width: '3em' }} />
                    <Column field="id" header={"Id"} filter={true} />
                    <Column field="createdOn" header={"Created On"} filter={true} />
                    <Column field="player" header={"Player"} filter={true} />
                  </DataTable>
                </div>
              </div>
            </Card>
          </div>
        </div>

        <form onSubmit={this.validarRegistro}>
          <Dialog position="center" visible={this.state.formDialogVisible} blockScroll={true}
            style={{ width: '50vw' }} modal={true} onHide={() => this.setState({ formDialogVisible: false })}>
            <div className="p-fluid">
              <div className="p-field p-grid">
                <label htmlFor="newGameRequestRows" className="p-col-12 p-md-2">Rows:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestRows" type="text" required={true} value={this.state.newGameRequestRows} />
                </div>

                <label htmlFor="newGameRequestCols" className="p-col-12 p-md-2">Cols:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestCols" type="text" required={true} value={this.state.newGameRequestCols} />
                </div>

                <label htmlFor="newGameRequestMined" className="p-col-12 p-md-2">Cols:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestMined" type="text" required={true} value={this.state.newGameRequestMined} />
                </div>

                <label htmlFor="newGameRequestBtn" className="p-col-12 p-md-2">Cols:</label>
                <div className="p-col-12 p-md-10">
                  <Button id="newGameRequestBtn" type="text" required={true} onClick={() => {this.requestNewGame()}} >Create</Button>
                </div>
              </div>
            </div>
            <div>
            </div>
          </Dialog>
        </form>
      </div>
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

  /**
   * <p>
   * User requests a new Game.
   * </p>
   * @param {*} rowData 
   */
  async requestNewGame() {
    const newGameRequest = {
      rows: this.state.newGameRequestRows , columns: this.state.newGameRequestCols, minedCells: this.state.newGameRequestMined
    }

    console.log(JSON.stringify(this.newGameRequest));
    const response = await fetch('http://localhost:18888/games/new', {
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
      body: JSON.stringify(newGameRequest) // body data type must match "Content-Type" header
    });
    return response.json(); // parses JSON response into native JavaScript objects
  }

  /**
    * Validar el registro del usuario.
    * @param {React.FormEvent} event Evento submit del formulario.
    */
  validate(event) {
    // todo
  }
}
