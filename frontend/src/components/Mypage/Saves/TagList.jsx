import styled from 'styled-components';

const TagList = ({ tag }) => {
	return (
		<Container>
			<ul>
				{tag.map((item) => {
					return (
						<List key={item}>
							<a href="123">{item}</a>
						</List>
					);
				})}
			</ul>
		</Container>
	);
};

export default TagList;

const Container = styled.div`
	display: flex;
	flex-wrap: wrap;
	gap: 4px;

	ul {
		display: inline;
		list-style: none;
		margin-left: 0;
		margin-bottom: 0.8rem;
	}
`;

const List = styled.li`
	display: inline;
	margin: 4px 4px 4px 0;

	a {
		display: inline-block;
		font-size: 12px;
		color: #39739d;
		background-color: #e1ecf4;
		padding: 0.4rem 0.5rem;
		margin: 0 2px 2px 0;
		line-height: 1;
		white-space: nowrap;
		text-decoration: none;
		text-align: center;
		border: 1px solid transparent;
		border-radius: 3px;
		font-weight: 600;
		:hover {
			background-color: #d1e3f0;
			color: #2c5877;
		}
	}
`;
