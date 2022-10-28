import { useEffect, useState } from 'react';
import styled from 'styled-components';
import Button from '../common/Button';

const PaginationStyle = styled.div`
	border-top: 1px solid rgb(209, 211, 215);
	margin-bottom: 48px;
	padding: 36px;
	display: flex;
	justify-content: space-between;

	button {
		margin: 2px;
		display: flex;
		align-items: center;
	}
	.active {
		background: rgb(229, 136, 62);
		color: white;
		border: none;
	}
	.pages {
		display: flex;

		div {
			display: flex;
			align-items: flex-end;
		}
	}

	.pagesize {
		display: flex;
		align-items: center;

		div {
			margin: 0 10px;
		}
	}
`;
const Pagination = ({ questionCount }) => {
	const [pageSize, setPageSize] = useState('15');
	const [pages, setPages] = useState([]);
	const [now, setNow] = useState(1);
	const pagesizeList = ['15', '30', '50'];
	const buttonProps = {
		color: `hsl(210,8%,25%)`,
		background: `white`,
		borderColor: `rgb(209, 211, 215)`,
		shadowPersent: '0',
		height: '25px',
	};
	useEffect(() => {
		let totalPage = Math.ceil(questionCount / pageSize);
		setPages(
			Array(totalPage)
				.fill()
				.map((v, i) => i + 1),
		);
	}, [pageSize]);

	const onClickPageSize = (size) => {
		setPageSize(size);
	};
	const onClickPage = (page) => {
		setNow(page);
	};
	const createPageButton = (page) => {
		return (
			<Button
				key={page}
				text={page}
				className={page === now && 'active'}
				{...buttonProps}
				callback={() => onClickPage(page)}
			/>
		);
	};
	return (
		<PaginationStyle>
			<div className="pages">
				{pages.map((page) => {
					if (now < 4 && page <= 5) {
						return createPageButton(page);
					} else {
						if (
							page === 1 ||
							(page >= now - 2 && page <= now + 2) ||
							page === pages.length
						) {
							return (
								<div key={page}>
									{page === pages.length && now < page - 2 && `...`}
									{createPageButton(page)}
									{page === 1 && now > page + 2 && `...`}
								</div>
							);
						}
					}
				})}
			</div>
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
