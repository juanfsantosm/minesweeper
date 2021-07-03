import { Button } from 'primereact/button';
import React from 'react';

export default class GridCell extends React.Component {

    constructor(props) {
        super(props);
        //this.uncover = this.uncover.bind(this);
    }

    render() {
        return (
            <Button onClick={this.props.uncover}>({this.props.adjacentMinedCells}:{this.props.status})</Button>
        );
    }

}