import { useEffect, useState } from 'react';
import styled from 'styled-components';
import Button from '../common/Button';

const PaginationStyle = styled.div`
	margin-top: 48px;
	padding: 36px;
	display: flex;
	justify-content: space-between;

	.pages {
		display: flex;
	}

	.pagesize {
		display: flex;
		align-items: center;

		.active {
			background: rgb(229, 136, 62);
			color: white;
			border: none;
		}
		div {
			margin: 0 10px;
		}
		button {
			margin: 2px;
			display: flex;
			align-items: center;
		}
	}
`;
const Pagination = ({ questionCount }) => {
	const [pageSize, setPageSize] = useState('15');
	const [page, setPage] = useState([]);
	const pagesizeList = ['15', '30', '50'];
	const buttonProps = {
		color: `hsl(210,8%,25%)`,
		background: `white`,
		borderColor: `rgb(209, 211, 215)`,
		shadowPersent: '0',
		height: '25px',
	};
	useEffect(() => {
		console.log('questionCount', questionCount);
		console.log('pageSize', pageSize);
		let totalPage = questionCount / pageSize;
		setPage(totalPage);
	}, [pageSize]);

	const onClickPageSize = (size) => {
		setPageSize(size);
	};
	return (
		<PaginationStyle>
			<div className="pages">{page}</div>
			<div className="pagesize">
				{pagesizeList.map((size, idx) => {
					return (
						<Button
							key={idx}
							text={size}
							className={size === pageSize && 'active'}
							{...buttonProps}
							callback={() => onClickPageSize(size)}
						/>
					);
				})}
				<div>per page</div>
			</div>
		</PaginationStyle>
	);
};

export default Pagination;
